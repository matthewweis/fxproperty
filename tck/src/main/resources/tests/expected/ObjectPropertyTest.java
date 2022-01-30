package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ObjectPropertyTest {
    @FxProperty
    ObjectProperty<Object> field = new SimpleObjectProperty<>(this, "field");

    public Object getField() {
        return field.getValue();
    }

    public ObjectProperty<Object> fieldProperty() {
        return field;
    }

    public void setField(Object field) {
        this.field.set(field);
    }
}