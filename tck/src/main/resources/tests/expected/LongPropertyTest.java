package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

public class LongPropertyTest {
    @FxProperty
    LongProperty field = new SimpleLongProperty(this, "field");

    public Long getField() {
        return field.getValue();
    }

    public LongProperty fieldProperty() {
        return field;
    }

    public void setField(Long field) {
        this.field.set(field);
    }
}