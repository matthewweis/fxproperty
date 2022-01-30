package org.fxproperty.javac;

import com.google.testing.compile.JavaFileObjects;
import org.fxproperty.tck.TechnologyCompatibilityKit;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTests implements TechnologyCompatibilityKit {

    private static final JavaCompiler COMPILER = ToolProvider.getSystemJavaCompiler();
    private static final String CLASSPATH = assembleClasspath();

    @Override
    public Class<?> compileWithoutProcessing(String expectedFileObjectPath, Path outDir, String className) {
        // setup options
        final List<String> options = List.of(
                "-Xlint:unchecked",
                "-Xdiags:verbose",
                "-classpath", CLASSPATH,
                "-d", pathString(outDir),
                "-proc:none"
        );

        // lookup object
        final JavaFileObject expected = getJavaFileObject(expectedFileObjectPath);

        // run compiler
        final JavaCompiler.CompilationTask task = createCompilationTask(options, expected);
        assertTrue(task.call());

        // read compiler output
        final URLClassLoader processedClassLoader = createUrlClassLoader(outDir);
        return assertDoesNotThrow(() -> Class.forName(className, true, processedClassLoader));
    }

    @Override
    public Class<?> compileWithProcessing(String actualFileObjectPath, Path outDir, String className) {
        // setup options
        final List<String> options = List.of(
                "-Xlint:unchecked",
                "-Xdiags:verbose",
                "-classpath", CLASSPATH,
                "-d", pathString(outDir),
                "-processor", "FxPropertyProcessor"
        );

        // lookup object
        final JavaFileObject actual = getJavaFileObject(actualFileObjectPath);

        // run compiler
        final JavaCompiler.CompilationTask task = createCompilationTask(options, actual);
        task.setProcessors(List.of(new FxPropertyProcessor())); // enable annotation processor
        assertTrue(task.call());

        // read compiler output
        final URLClassLoader unprocessedClassLoader = createUrlClassLoader(outDir);
        return assertDoesNotThrow(() -> Class.forName(className, true, unprocessedClassLoader));
    }

    private JavaCompiler.CompilationTask createCompilationTask(List<String> options, JavaFileObject fileObject) {
        return COMPILER.getTask(null, null, null, options, Collections.emptyList(), List.of(fileObject));
    }

    private static JavaFileObject getJavaFileObject(String sourcePath) {
        final URL source = assertDoesNotThrow(() -> requireNonNull(TechnologyCompatibilityKit.class.getResource(sourcePath)));
        final URI uri = assertDoesNotThrow(source::toURI);
        final URL url = assertDoesNotThrow(uri::toURL);
        return JavaFileObjects.forResource(url);
    }

    private static String pathString(Path path) {
        return path.toString(); // todo use absolute path? (if not inline this method)
    }

    private static URLClassLoader createUrlClassLoader(Path path) {
        return URLClassLoader.newInstance(new URL[]{ getUrl(path) });
    }

    private static URL getUrl(Path path) {
        final URI uri = assertDoesNotThrow(path::toUri);
        return assertDoesNotThrow(uri::toURL);
    }

    private static String assembleClasspath() {
        final String expectedClasspath = System.getProperty("java.class.path");
        final Path bundledClasses = Paths.get("target", "classes");
        final Path packagedAnnotationApi = loadFxPropertyApiJar();
        return bundledClasses + ":" + expectedClasspath + ":" + packagedAnnotationApi;
    }

    public static Path loadFxPropertyApiJar() {
        final String url = Objects.requireNonNull(System.getProperty("api-jar"),
                "System property 'api-jar' was expected to have been set by maven surefire plugin.");
        final String formatted = url.startsWith("/") ? url : String.format("/%s", url);

        try {
            final URL resource = TechnologyCompatibilityKit.class.getResource(formatted);
            if (resource == null) {
                final String className = TechnologyCompatibilityKit.class.getName();
                final String detailMessage = url + " was specified by maven, yet it is missing from the build";
                throw new MissingResourceException(detailMessage, className, formatted);
            } else {
                return Path.of(resource.toURI());
            }
        } catch (URISyntaxException | MissingResourceException e) {
            e.printStackTrace(System.err);
            throw new RuntimeException(e);
        }
    }

}
