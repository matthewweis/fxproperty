package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleFloatProperty;

public class SimpleFloatPropertyTest {
    @FxProperty
    SimpleFloatProperty field = new SimpleFloatProperty(this, "field");
}