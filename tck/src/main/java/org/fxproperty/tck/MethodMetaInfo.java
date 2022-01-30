package org.fxproperty.tck;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

final class MethodMetaInfo {
    private final String name;
    private final Class<?> returnType;
    private final Class<?>[] parameterTypes;

    MethodMetaInfo(String name, Class<?> returnType, Class<?>[] parameterTypes) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.returnType = Objects.requireNonNull(returnType, "returnType must not be null");
        this.parameterTypes = Objects.requireNonNull(parameterTypes, "parameterTypes must not be null");
    }

    @Override
    public String toString() {
        final String prettyArgsString = Arrays.stream(parameterTypes)
                .map(Class::getTypeName)
                .map(Object::toString)
                .collect(Collectors.joining(", ", "(", ")"));
        return returnType.getTypeName() + " " + name + prettyArgsString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodMetaInfo that = (MethodMetaInfo) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(returnType, that.returnType) &&
                Arrays.equals(parameterTypes, that.parameterTypes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, returnType);
        result = 31 * result + Arrays.hashCode(parameterTypes);
        return result;
    }
}
