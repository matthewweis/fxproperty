package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleLongProperty;

public class SimpleLongPropertyTest {
    @FxProperty
    SimpleLongProperty field = new SimpleLongProperty(this, "field");

    public Long getField() {
        return field.getValue();
    }

    public SimpleLongProperty fieldProperty() {
        return field;
    }

    public void setField(Long field) {
        this.field.set(field);
    }
}