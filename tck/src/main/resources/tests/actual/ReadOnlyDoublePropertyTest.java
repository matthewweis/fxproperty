package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class ReadOnlyDoublePropertyTest {
    @FxProperty
    ReadOnlyDoubleProperty field = new SimpleDoubleProperty(this, "field");
}