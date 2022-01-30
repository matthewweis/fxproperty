package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class FloatPropertyTest {
    @FxProperty
    FloatProperty field = new SimpleFloatProperty(this, "field");

    public Float getField() {
        return field.getValue();
    }

    public FloatProperty fieldProperty() {
        return field;
    }

    public void setField(Float field) {
        this.field.set(field);
    }
}