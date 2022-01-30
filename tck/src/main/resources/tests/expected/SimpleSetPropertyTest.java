package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.*;
import javafx.collections.ObservableSet;

public class SimpleSetPropertyTest {
    @FxProperty
    SimpleSetProperty<Object> field = new SimpleSetProperty<>(this, "field");

    public ObservableSet<Object> getField() {
        return field.get();
    }

    public SimpleSetProperty<Object> fieldProperty() {
        return field;
    }

    public void setField(ObservableSet<Object> field) {
        this.field.set(field);
    }
}