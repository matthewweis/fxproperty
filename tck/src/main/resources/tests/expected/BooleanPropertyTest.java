package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class BooleanPropertyTest {
    @FxProperty
    BooleanProperty field = new SimpleBooleanProperty(this, "field");

    public Boolean getField() {
        return field.getValue();
    }

    public BooleanProperty fieldProperty() {
        return field;
    }

    public void setField(Boolean field) {
        this.field.set(field);
    }
}