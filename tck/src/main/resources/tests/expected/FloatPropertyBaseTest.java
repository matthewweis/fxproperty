package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.FloatPropertyBase;
import javafx.beans.property.SimpleFloatProperty;

public class FloatPropertyBaseTest {
    @FxProperty
    FloatPropertyBase field = new SimpleFloatProperty(this, "field");

    public Float getField() {
        return field.getValue();
    }

    public FloatPropertyBase fieldProperty() {
        return field;
    }

    public void setField(Float field) {
        this.field.set(field);
    }
}