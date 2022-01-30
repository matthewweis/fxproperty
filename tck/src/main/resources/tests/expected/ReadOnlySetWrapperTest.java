package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlySetWrapper;
import javafx.collections.ObservableSet;

public class ReadOnlySetWrapperTest {
    @FxProperty
    ReadOnlySetWrapper<Object> field = new ReadOnlySetWrapper<>(this, "field");

    public ObservableSet<Object> getField() {
        return field.get();
    }

    public ReadOnlySetWrapper<Object> fieldProperty() {
        return field;
    }

    public void setField(ObservableSet<Object> field) {
        this.field.set(field);
    }
}