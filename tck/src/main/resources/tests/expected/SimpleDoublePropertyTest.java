package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SimpleDoublePropertyTest {
    @FxProperty
    SimpleDoubleProperty field = new SimpleDoubleProperty(this, "field");

    public Double getField() {
        return field.getValue();
    }

    public SimpleDoubleProperty fieldProperty() {
        return field;
    }

    public void setField(Double field) {
        this.field.set(field);
    }
}