package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SimpleIntegerPropertyTest {
    @FxProperty
    SimpleIntegerProperty field = new SimpleIntegerProperty(this, "field");

    public Integer getField() {
        return field.getValue();
    }

    public SimpleIntegerProperty fieldProperty() {
        return field;
    }

    public void setField(Integer field) {
        this.field.set(field);
    }
}