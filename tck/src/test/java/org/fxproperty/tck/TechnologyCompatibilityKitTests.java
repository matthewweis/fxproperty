package org.fxproperty.tck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TechnologyCompatibilityKitTests {

    @Test
    public void executionPlanMatchesMavenConfiguration() {
        Assertions.assertEquals("/tests.csv", TechnologyCompatibilityKit.EXECUTION_PLAN);
    }

    @Test
    public void packageNameMatchesMavenConfiguration() {
        assertEquals("org.fxproperty.tck", TechnologyCompatibilityKit.PACKAGE_NAME);
    }

}
