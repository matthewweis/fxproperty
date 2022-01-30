package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class DoublePropertyTest {
    @FxProperty
    DoubleProperty field = new SimpleDoubleProperty(this, "field");
}