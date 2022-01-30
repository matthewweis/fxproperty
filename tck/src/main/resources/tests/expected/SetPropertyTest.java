package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.*;
import javafx.collections.ObservableSet;

public class SetPropertyTest {
    @FxProperty
    SetProperty<Object> field = new SimpleSetProperty<>(this, "field");

    public ObservableSet<Object> getField() {
        return field.get();
    }

    public SetProperty<Object> fieldProperty() {
        return field;
    }

    public void setField(ObservableSet<Object> field) {
        this.field.set(field);
    }
}