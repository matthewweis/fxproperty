package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.MapPropertyBase;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.ObservableMap;

public class MapPropertyBaseTest {
    @FxProperty
    MapPropertyBase<Object, Object> field = new SimpleMapProperty<>(this, "field");

    public ObservableMap<Object, Object> getField() {
        return field.getValue();
    }

    public MapPropertyBase<Object, Object> fieldProperty() {
        return field;
    }

    public void setField(ObservableMap<Object, Object> field) {
        this.field.set(field);
    }
}