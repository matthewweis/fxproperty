package org.fxproperty.javac;

import com.google.testing.compile.JavaFileObjects;
import org.fxproperty.tck.TechnologyCompatibilityKit;
import org.junit.jupiter.api.Test;

import javax.lang.model.element.TypeElement;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.lang.reflect.Type;
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

public class UnitTests {

    @Test
    public void sanityTest() {

    }

}
