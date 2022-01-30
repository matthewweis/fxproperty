package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.SimpleDoubleProperty;

public class DoublePropertyBaseTest {
    @FxProperty
    DoublePropertyBase field = new SimpleDoubleProperty(this, "field");

    public Double getField() {
        return field.getValue();
    }

    public DoublePropertyBase fieldProperty() {
        return field;
    }

    public void setField(Double field) {
        this.field.set(field);
    }
}