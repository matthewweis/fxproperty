package org.fxproperty.javac;

import com.sun.tools.javac.code.*;
import com.sun.tools.javac.comp.TransTypes;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import org.fxproperty.api.Visibility;
import org.fxproperty.api.FxProperty;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.type.TypeMirror;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

class PropertyTreeTranslator extends TreeTranslator {

    private final Symbol.ClassSymbol property;
    private final Symbol.ClassSymbol readOnlyProperty;
    @SuppressWarnings("FieldCanBeLocal")
    private final Symbol.ClassSymbol observableValue;
    @SuppressWarnings("FieldCanBeLocal")
    private final Symbol.ClassSymbol writeableValue;

    private final Name getValueMethodName;
    private final Name setValueMethodName;
    private final Name getMethodName;
    private final Name setMethodName;

    private final JavacTypes typeUtils;
    private final JavacElements elementUtils;
    private final Types types;
    private final Names names;
    private final TreeMaker treeMaker;
    private final Symtab symtab;
    private final TransTypes transTypes;

    /**
     * Originally named "PendingFxPropertyFields."
     * The outer deque tracks the current class being visited (see {@link PropertyTreeTranslator#visitClassDef(JCTree.JCClassDecl)}).
     * The inner deque tracks the @FxProperty annotated fields to generate methods from (see {@link PropertyTreeTranslator#visitVarDef(JCTree.JCVariableDecl)}).
     */
    private final Deque<Deque<JCTree.JCVariableDecl>> pendingFields;

    PropertyTreeTranslator(JavacTypes typeUtils, JavacElements elementUtils, Types types, Names names, TreeMaker treeMaker, Symtab symtab, TransTypes transTypes) {
        this.typeUtils = typeUtils;
        this.elementUtils = elementUtils;
        this.types = types;
        this.names = names;
        this.treeMaker = treeMaker;
        this.property = elementUtils.getTypeElement("javafx.beans.property.Property");
        this.readOnlyProperty = elementUtils.getTypeElement("javafx.beans.property.ReadOnlyProperty");
        this.observableValue = elementUtils.getTypeElement("javafx.beans.value.ObservableValue");
        this.writeableValue = elementUtils.getTypeElement("javafx.beans.value.WritableValue");
        this.getValueMethodName = elementUtils.getName("getValue");
        this.setValueMethodName = elementUtils.getName("setValue");
        this.getMethodName = elementUtils.getName("get");
        this.setMethodName = elementUtils.getName("set");
        this.symtab = symtab;
        this.transTypes = transTypes;
        pendingFields = new LinkedList<>(); // todo: CONSIDER ArrayDequeue OR ConcurrentLinkedDequeue
    }


    @Override
    public void visitVarDef(JCTree.JCVariableDecl tree) {
        // if a @FxProperty annotation is found, push it to the current classes members stack
        Optional.ofNullable(tree.sym)
                .filter(sym -> Objects.nonNull(sym.getAnnotation(FxProperty.class)))
                .map(Symbol::enclClass)
                .ifPresent(classSymbol -> Objects.requireNonNull(pendingFields.peek()).push(tree));
        super.visitVarDef(tree);
    }

    @Override
    public void visitClassDef(JCTree.JCClassDecl tree) {
        pendingFields.push(new ArrayDeque<>());
        tree.mods = translate(tree.mods);
        tree.typarams = translateTypeParams(tree.typarams);
        tree.extending = translate(tree.extending);
        tree.implementing = translate(tree.implementing);
        tree.defs = translate(tree.defs);
        Objects.<Collection<JCTree.JCVariableDecl>>requireNonNullElseGet(pendingFields.pop(), Collections::emptyList)
                .stream()
                .filter(annotatedField -> Objects.nonNull(annotatedField.sym))
                .filter(annotatedField -> Objects.nonNull(annotatedField.sym.type))
                .filter(annotatedField -> Objects.nonNull(annotatedField.sym.getAnnotation(FxProperty.class)))
                .forEach(annotatedField -> {
                    final FxProperty fxProperty = Objects.requireNonNull(
                            annotatedField.sym.getAnnotation(FxProperty.class),
                            "INTERNAL ERROR: Attempted to process unannotated tree.");

                    final boolean isProperty = property.isInheritedIn(annotatedField.sym, types);
                    final boolean isReadOnlyProperty = isProperty || readOnlyProperty.isInheritedIn(annotatedField.sym, types);

                    final Stream<? extends JCTree> preexistingMembers = tree.defs.stream();
                    final Stream<? extends JCTree.JCMethodDecl> generatedMembers = conditionalStream(isReadOnlyProperty, () -> {
                        final Type type = annotatedField.sym.type;
                        final TypeMirror qualifiedGenericType = type.accept(new PropertyTypeResolver(elementUtils, typeUtils, symtab), null);
                        final Element ge = typeUtils.asElement(qualifiedGenericType);
                        final Symbol.ClassSymbol cs = elementUtils.getTypeElement(ge.toString());

                        final Symbol fieldName = annotatedField.sym.asMemberOf(property.type, types);
                        final JCTree.JCExpression field = treeMaker.Ident(fieldName);

                        final Optional<JCTree.JCMethodDecl> gm = createGetter(fxProperty, type, fieldName, field);
                        final Optional<JCTree.JCMethodDecl> pm = createPropertyGetter(fxProperty, type, fieldName, field);
                        final Optional<JCTree.JCMethodDecl> sm = conditionalOptional(isProperty, () -> createSetter(tree, fxProperty, type, fieldName, field));
                        return Stream.of(gm, pm, sm).flatMap(Optional::stream);
                    });

                    final Stream<? extends JCTree> defs = Stream.concat(preexistingMembers, generatedMembers);
                    tree.defs = defs.collect(List.collector());
                });
    }

    private Optional<JCTree.JCMethodDecl> createPropertyGetter(FxProperty fxProperty, Type type, Symbol fieldName, JCTree.JCExpression field) {
        // todo check for preexisting method and return empty stream if one exists
        //   - consider equivalent -Werror flag for nominal collision (maybe -Wnominal)
        return Optional.of(treeMaker.MethodDef(
                treeMaker.Modifiers(makeFlags(fxProperty.property())), // modifiers todo adjust to ann value
                postfixName(fieldName, "Property"),
                treeMaker.Type(type), // return type
                List.nil(), // no type params
                List.nil(), // no params
                List.nil(), // throws no exceptions
                treeMaker.Block(0x0L, List.of(treeMaker.Return(field))),
                null // only applies to annotations
        ));
    }

    private Optional<JCTree.JCMethodDecl> createGetter(FxProperty fxProperty, Type type, Symbol fieldName, JCTree.JCExpression field) {
        // todo check for preexisting method and return empty stream if one exists
        //   - consider equivalent -Werror flag for nominal collision (maybe -Wnominal)
        return findGetter(type)
                .map(Symbol::baseSymbol) // puts the parens "()" back on the end
                // todo instead handle case of multiple "correct" methods with varying type specificity
                .map(fieldGetValue -> {
                    final Type genericType = types.erasureRecursive(types.boxedTypeOrType(fieldGetValue.asType().getReturnType()));
                    return treeMaker.MethodDef(
                            treeMaker.Modifiers(makeFlags(fxProperty.getter())), // modifiers todo adjust to ann value
                            prefixName("get", fieldName), // name
                            treeMaker.Type(genericType), // return type
                            List.nil(), // no type params
                            List.nil(), // no params
                            List.nil(), // throws no exceptions
                            treeMaker.Block(0x0L, List.of(treeMaker.Return(treeMaker.App(treeMaker.Select(field, fieldGetValue))))),
                            null // only applies to annotations
                    );
                });
    }

    private Optional<JCTree.JCMethodDecl> createSetter(JCTree.JCClassDecl tree, FxProperty fxProperty, Type type, Symbol fieldName, JCTree.JCExpression field) {
        return findSetter(type)
                .map(Symbol::baseSymbol) // puts the parens "()" back on the end
                .map(fieldSetValue -> {
                    final Type genericType = types.erasureRecursive(types.boxedTypeOrType(fieldSetValue.asType().getParameterTypes().head));
                    final JCTree.JCVariableDecl setterParam = transTypes.translate(treeMaker.Param(names.fromString("value"), genericType, tree.sym));
                    final JCTree.JCExpression fieldSelector = transTypes.translate(treeMaker.Select(field, fieldSetValue));
                    final List<JCTree.JCExpression> setterArgs = List.of(transTypes.translate(treeMaker.Ident(setterParam)));
                    final JCTree.JCExpressionStatement setterExecutor = treeMaker.Exec(treeMaker.App(fieldSelector, setterArgs));
                    return treeMaker.MethodDef(
                            treeMaker.Modifiers(makeFlags(fxProperty.setter())), // modifiers todo adjust to ann value
                            prefixName("set", fieldName), // method name
                            treeMaker.Type(symtab.voidType), // return type
                            List.nil(), // no type params
                            List.of(setterParam), // no params
                            List.nil(), // throws no exceptions
                            treeMaker.Block(0x0L, List.of(setterExecutor)), // method body
                            null // only applies to annotations
                    );
                });
    }

    private Stream<Type> explodeType(Type nullable) {
        return Stream.concat(Stream.ofNullable(nullable), supers(nullable));
    }

    private Stream<Type> supers(Type nullable) {
        return Stream.ofNullable(nullable).flatMap(type -> {
            final Stream<Type> interfaces = types.interfaces(type).stream().flatMap(this::explodeType);
            final Stream<Type> supertype = Stream.ofNullable(types.supertype(type)).filter(st -> st != type).flatMap(this::explodeType);
            return Stream.concat(supertype, interfaces);
        });
    }

    private Optional<Symbol> findGetter(Type nullable) {
        return Stream.ofNullable(nullable)
                .flatMap(this::explodeType)
                .flatMap(parent -> Stream.ofNullable(parent.tsym)
                        .flatMap(typeSymbol -> typeSymbol.getEnclosedElements().stream()))
                .filter(enclosed -> enclosed.getKind().equals(ElementKind.METHOD)) // is method?
                .filter(enclosed -> { // name matches?
                    final Name qualifiedName = enclosed.getQualifiedName();
                    return qualifiedName.equals(getValueMethodName) || qualifiedName.equals(getMethodName);
                })
                .filter(enclosed -> enclosed.asType().getParameterTypes().head == null) // no params?
                .reduce((s1, s2) -> {
                    final Type s1RetType = types.erasureRecursive(
                            Objects.requireNonNull(s1.asType().getReturnType(),
                            "INTERNAL ERROR: Getter must have return type."));
                    final Type s2RetType = types.erasureRecursive(
                            Objects.requireNonNull(s2.asType().getReturnType(),
                            "INTERNAL ERROR: Getter must have return type."));

                    final boolean cannotReify1 = !types.isReifiable(s1RetType);
                    final boolean cannotReify2 = !types.isReifiable(s2RetType);
                    if (cannotReify1 && cannotReify2) {
                        System.err.println("INTERNAL ERROR: Hit cannotReify1 && cannotReify2 in findGetter().");
                    } else if (cannotReify1 ^ cannotReify2) {
                        return cannotReify1 ? s2 : s1;
                    }

                    // handle specialized properties for primitive types
                    final boolean sameType = types.isSameType(s1RetType, s2RetType);
                    final boolean sameWhenBoxed = types.isSameType(types.boxedTypeOrType(s1RetType), types.boxedTypeOrType(s2RetType));
                    if (!sameType && sameWhenBoxed) {
                        if (s1RetType.isPrimitive()) {
                            return s2;
                        } else if (s2RetType.isPrimitive()) {
                            return s1;
                        } else {
                            throw new IllegalStateException("INTERNAL ERROR: At least one type should be primitive.");
                        }
                    }

                    if (types.isAssignable(s1RetType, s2RetType)) return s1;
                    if (types.isAssignable(s2RetType, s1RetType)) return s2;
                    throw new IllegalStateException("INTERNAL ERROR: Incompatible types.");
                });
    }

    private Optional<Symbol> findSetter(Type nullable) {
        return Stream.ofNullable(nullable)
                .flatMap(this::explodeType)
                .flatMap(parent -> Stream.ofNullable(parent.tsym)
                        .flatMap(typeSymbol -> typeSymbol.getEnclosedElements().stream()))
                .filter(enclosed -> enclosed.getKind().equals(ElementKind.METHOD)) // is method?
                .filter(enclosed -> { // name matches?
                    final Name qualifiedName = enclosed.getQualifiedName();
                    return qualifiedName.equals(setValueMethodName) || qualifiedName.equals(setMethodName);
                })
                .filter(enclosed -> types.isSameType(enclosed.asType().getReturnType(), symtab.voidType)) // returns void?
                .filter(enclosed -> enclosed.asType().getParameterTypes().head != null) // has 1 arg?
                .reduce((s1, s2) -> {
                    final Type s1ArgType = types.erasureRecursive(Objects.requireNonNull(s1.asType().getParameterTypes().head,
                            "INTERNAL ERROR: Type params should be checked in prior filter."));
                    final Type s2ArgType = types.erasureRecursive(Objects.requireNonNull(s2.asType().getParameterTypes().head,
                            "INTERNAL ERROR: Type params should be checked in prior filter."));

                    final boolean cannotReify1 = !types.isReifiable(s1ArgType);
                    final boolean cannotReify2 = !types.isReifiable(s2ArgType);
                    if (cannotReify1 && cannotReify2) {
                        System.err.println("INTERNAL ERROR: Hit cannotReify1 && cannotReify2 in findSetter()");
                    } else if (cannotReify1 ^ cannotReify2) {
                        return cannotReify1 ? s2 : s1;
                    }

                    final boolean sameType = types.isSameType(s1ArgType, s2ArgType);
                    final boolean sameWhenBoxed = types.isSameType(types.boxedTypeOrType(s1ArgType), types.boxedTypeOrType(s2ArgType));
                    if (!sameType && sameWhenBoxed) {
                        // if we have primitive boxed and unboxed versions
                        if (s1ArgType.isPrimitive()) {
                            return s2;
                        } else if (s2ArgType.isPrimitive()) {
                            return s1;
                        } else {
                            throw new IllegalStateException("INTERNAL ERROR: At least one type should be primitive");
                        }
                    }

                    if (types.isAssignable(s1ArgType, s2ArgType)) return s1;
                    if (types.isAssignable(s2ArgType, s1ArgType)) return s2;
                    throw new IllegalStateException("INTERNAL ERROR: Incompatible types.");
                });
    }

    private long makeFlags(Visibility visibility) {
        switch (visibility) {
            case PUBLIC:
                return Flags.PUBLIC;
            case PROTECTED:
                return Flags.PROTECTED;
            case DEFAULT:
                return 0L;
            case PRIVATE:
                return Flags.PRIVATE;
            default:
                throw new IllegalStateException("INTERNAL ERROR: Non-exhaustive pattern match.");
        }
    }

    private static <T> Stream<T> conditionalStream(boolean condition, Supplier<Stream<T>> supplier) {
        return condition ? supplier.get() : Stream.empty();
    }

    private static <T> Optional<T> conditionalOptional(boolean condition, Supplier<Optional<T>> supplier) {
        return condition ? supplier.get() : Optional.empty();
    }

    private static String capitalizeFirstLetter(String s) {
        return transformSubstring(s, 0, 1, String::toUpperCase);
    }

    private static String lowercaseFirstLetter(String s) {
        return transformSubstring(s, 0, 1, String::toLowerCase);
    }

    private static String transformSubstring(String s, int beginIndex, int endIndex, Function<String,String> transformer) {
        if (beginIndex <= endIndex && endIndex < s.length()) {
            return transformer.apply(s.substring(beginIndex, endIndex)) + s.substring(endIndex);
        } else {
            return s;
        }
    }

    private Name prefixName(String prefix, Symbol name) {
        return names.fromString(lowercaseFirstLetter(prefix) + capitalizeFirstLetter(name.toString()));
    }

    private Name postfixName(Symbol name, String postfix) {
        return names.fromString(lowercaseFirstLetter(name.toString()) + capitalizeFirstLetter(postfix));
    }
}
