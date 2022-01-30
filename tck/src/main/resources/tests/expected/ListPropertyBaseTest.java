package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ListPropertyBase;
import javafx.beans.property.SimpleListProperty;

public class ListPropertyBaseTest {
    @FxProperty
    ListPropertyBase<Object> field = new SimpleListProperty<Object>(this, "field");

    public javafx.collections.ObservableList<Object> getField() {
        return field.getValue();
    }

    public ListPropertyBase<Object> fieldProperty() {
        return field;
    }

    public void setField(javafx.collections.ObservableList<Object> field) {
        this.field.set(field);
    }
}