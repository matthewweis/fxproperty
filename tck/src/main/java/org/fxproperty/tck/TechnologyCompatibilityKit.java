package org.fxproperty.tck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface TechnologyCompatibilityKit {

    String PACKAGE_NAME = TechnologyCompatibilityKit.class.getPackage().getName();
    String EXECUTION_PLAN = "/tests.csv";

    Class<?> compileWithoutProcessing(String expectedFileObjectPath, Path outDir, String className);

    Class<?> compileWithProcessing(String actualFileObjectPath, Path outDir, String className);

    @ParameterizedTest
    @MethodSource("tests") // a module-aware version of "@CsvFileSource(resources = "/tests.csv")"
    default void scenarios(String classFileName, @TempDir Path folder) {
        // setup filesystem
        final String className = PACKAGE_NAME + "." + removeExtension(classFileName);
        final Path expectedResults = folder.resolve("expected");
        final Path actualResults = folder.resolve("actual");

        // read inputs
        Assertions.assertNotNull(classFileName);
        final String actual = String.format("/tests/actual/%s", classFileName);
        final String expected = String.format("/tests/expected/%s", classFileName);

        // compile
        final Class<?> unprocessed = compileWithoutProcessing(expected, expectedResults, className);
        final Class<?> processed = compileWithProcessing(actual, actualResults, className);

        // compare results
        compareMethods(unprocessed, processed);
    }

    // must be public due to usage @MethodSource("tests")
    static Stream<? extends Arguments> tests() {
        final InputStream nullable = TechnologyCompatibilityKit.class.getResourceAsStream(EXECUTION_PLAN);
        final InputStream bytes = Objects.requireNonNull(nullable, "unable to read execution plan");
        final InputStreamReader chars = new InputStreamReader(bytes);
        final BufferedReader buffered = new BufferedReader(chars);
        final Stream<? extends String> lines = buffered.lines();
        return lines.map(Arguments::of);
    }

    private void compareMethods(Class<?> expected, Class<?> actual) {
        final Set<? extends MethodMetaInfo> unprocessedMethods = methods(expected);
        final Set<? extends MethodMetaInfo> processedMethods = methods(actual);
        Assertions.assertEquals(unprocessedMethods, processedMethods);
    }

    private static Set<? extends MethodMetaInfo> methods(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .map(m -> new MethodMetaInfo(m.getName(), m.getReturnType(), m.getParameterTypes()))
                .collect(Collectors.toUnmodifiableSet());
    }

    private static String removeExtension(String classFileName) {
        final int dotIndex = classFileName.lastIndexOf('.');
        if (dotIndex != -1) {
            return classFileName.substring(0, dotIndex);
        }
        return classFileName;
    }

}
