package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class SimpleBooleanPropertyTest {
    @FxProperty
    SimpleBooleanProperty field = new SimpleBooleanProperty(this, "field");

    public Boolean getField() {
        return field.getValue();
    }

    public SimpleBooleanProperty fieldProperty() {
        return field;
    }

    public void setField(Boolean field) {
        this.field.set(field);
    }
}