package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SetPropertyBase;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.ObservableSet;

public class SetPropertyBaseTest {
    @FxProperty
    SetPropertyBase<Object> field = new SimpleSetProperty<>(this, "field");

    public ObservableSet<Object> getField() {
        return field.get();
    }

    public SetPropertyBase<Object> fieldProperty() {
        return field;
    }

    public void setField(ObservableSet<Object> field) {
        this.field.set(field);
    }
}