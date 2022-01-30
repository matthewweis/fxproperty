package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleObjectProperty;

public class ObjectPropertyBaseTest {
    @FxProperty
    ObjectPropertyBase<Object> field = new SimpleObjectProperty<>(this, "field");

    public Object getField() {
        return field.getValue();
    }

    public ObjectPropertyBase<Object> fieldProperty() {
        return field;
    }

    public void setField(Object field) {
        this.field.set(field);
    }
}