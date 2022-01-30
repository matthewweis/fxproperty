package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.FloatPropertyBase;
import javafx.beans.property.SimpleFloatProperty;

public class FloatPropertyBaseTest {
    @FxProperty
    FloatPropertyBase field = new SimpleFloatProperty(this, "field");
}