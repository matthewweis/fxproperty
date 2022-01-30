package org.fxproperty.javac;

import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.model.JavacTypes;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleElementVisitor9;
import java.util.stream.Stream;

class PropertyElementResolver extends SimpleElementVisitor9<TypeMirror, TypeMirror> {

    private final JavacElements elementUtils;
    private final JavacTypes typeUtils;
    private final Symtab symtab;

    PropertyElementResolver(JavacElements elementUtils, JavacTypes typeUtils, Symtab symtab) {
        this.elementUtils = elementUtils;
        this.typeUtils = typeUtils;
        this.symtab = symtab;
    }

    @Override
    public TypeMirror visitType(TypeElement e, TypeMirror last) {
        return supers(e)
                .flatMap(s -> Stream.ofNullable(s.accept(new PropertyTypeResolver(elementUtils, typeUtils, symtab), last)))
                .findFirst()
                .orElse(null);
    }

    private Stream<TypeMirror> supers(TypeElement e) {
        return Stream.concat(Stream.ofNullable(e.getSuperclass()), e.getInterfaces().stream());
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("PropertyElementResolver cannot create a hash code.");
    }

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("PropertyElementResolver cannot test equality.");
    }
}
