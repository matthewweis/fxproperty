package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.ObservableMap;

public class SimpleMapPropertyTest {
    @FxProperty
    SimpleMapProperty<Object, Object>  field = new SimpleMapProperty<>(this, "field");

    public ObservableMap<Object, Object> getField() {
        return field.get();
    }

    public SimpleMapProperty<Object, Object> fieldProperty() {
        return field;
    }

    public void setField(ObservableMap<Object, Object> field) {
        this.field.set(field);
    }
}