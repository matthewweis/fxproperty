package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class IntegerPropertyTest {
    @FxProperty
    IntegerProperty field = new SimpleIntegerProperty(this, "field");

    public Integer getField() {
        return field.getValue();
    }

    public IntegerProperty fieldProperty() {
        return field;
    }

    public void setField(Integer field) {
        this.field.set(field);
    }
}