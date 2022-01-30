package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.IntegerPropertyBase;
import javafx.beans.property.SimpleIntegerProperty;

public class IntegerPropertyBaseTest {
    @FxProperty
    IntegerPropertyBase field = new SimpleIntegerProperty(this, "field");

    public Integer getField() {
        return field.getValue();
    }

    public IntegerPropertyBase fieldProperty() {
        return field;
    }

    public void setField(Integer field) {
        this.field.set(field);
    }
}