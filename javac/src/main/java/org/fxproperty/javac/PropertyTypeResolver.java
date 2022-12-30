package org.fxproperty.javac;

import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.comp.TransTypes;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.model.JavacTypes;

import javax.lang.model.element.Element;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleTypeVisitor9;

class PropertyTypeResolver extends SimpleTypeVisitor9<TypeMirror, TypeMirror> {

    private final JavacElements elementUtils;
    private final JavacTypes typeUtils;
    private final Symtab symtab;
    private final TransTypes transTypes = null; // todo

    public PropertyTypeResolver(JavacElements elementUtils, JavacTypes typeUtils, Symtab symtab) {
        this.elementUtils = elementUtils;
        this.typeUtils = typeUtils;
        this.symtab = symtab;
    }

    @Override
    public TypeMirror visitDeclared(DeclaredType t, TypeMirror last) {
        final Element e = t.asElement();
        final Symbol.ClassSymbol property =
                elementUtils.getTypeElement("javafx.beans.property.Property");
        final Symbol.ClassSymbol readOnlyProperty =
                elementUtils.getTypeElement("javafx.beans.property.ReadOnlyProperty");

        final boolean isProperty = typeUtils.isAssignable(typeUtils.erasure(t), property.asType());
        final boolean isReadOnlyProperty = typeUtils.isAssignable(typeUtils.erasure(t), readOnlyProperty.asType());

        if (isProperty || isReadOnlyProperty) {
            if (t.getTypeArguments().isEmpty()) {
                // occurs when generic-parameterized classes are used with rawtypes.

                // option 1: convert rawtypes to Object
                return elementUtils.getTypeElement("java.lang.Object").asType();

                // option 2: throw error
                // throw new IllegalStateException("FxProperty internal error: expected property to have type args.");

                // option 3: generate properties with rawtypes
            }
            return t.getTypeArguments().get(0);
        } else {
            return e.accept(new PropertyElementResolver(elementUtils, typeUtils, symtab), last);
        }
    }
}
