package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyMapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.ObservableMap;

public class ReadOnlyMapPropertyTest {
    @FxProperty
    ReadOnlyMapProperty<Object, Object> field = new SimpleMapProperty<Object, Object>(this, "field");

    public ObservableMap<Object, Object> getField() {
        return field.getValue();
    }

    public ReadOnlyMapProperty<Object, Object> fieldProperty() {
        return field;
    }
}