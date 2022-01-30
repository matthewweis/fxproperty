package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SimpleDoublePropertyTest {
    @FxProperty
    SimpleDoubleProperty field = new SimpleDoubleProperty(this, "field");
}