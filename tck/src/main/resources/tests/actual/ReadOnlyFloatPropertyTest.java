package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyFloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class ReadOnlyFloatPropertyTest {
    @FxProperty
    ReadOnlyFloatProperty field = new SimpleFloatProperty(this, "field");
}