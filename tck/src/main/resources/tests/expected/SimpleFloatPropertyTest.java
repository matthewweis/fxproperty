package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleFloatProperty;

public class SimpleFloatPropertyTest {
    @FxProperty
    SimpleFloatProperty field = new SimpleFloatProperty(this, "field");

    public Float getField() {
        return field.getValue();
    }

    public SimpleFloatProperty fieldProperty() {
        return field;
    }

    public void setField(Float field) {
        this.field.set(field);
    }
}