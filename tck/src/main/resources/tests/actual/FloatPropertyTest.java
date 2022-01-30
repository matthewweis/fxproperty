package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class FloatPropertyTest {
    @FxProperty
    FloatProperty field = new SimpleFloatProperty(this, "field");
}