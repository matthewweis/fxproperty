package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyMapWrapper;
import javafx.collections.ObservableMap;

public class ReadOnlyMapWrapperTest {
    @FxProperty
    ReadOnlyMapWrapper<Object, Object> field = new ReadOnlyMapWrapper<>(this, "field");

    public ObservableMap<Object, Object> getField() {
        return field.get();
    }

    public ReadOnlyMapWrapper<Object, Object> fieldProperty() {
        return field;
    }

    public void setField(ObservableMap<Object, Object> field) {
        this.field.set(field);
    }
}