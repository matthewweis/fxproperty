package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;

public class ListPropertyTest {
    @FxProperty
    ListProperty<Object> field = new SimpleListProperty<>(this, "field");

    public ObservableList<Object> getField() {
        return field.getValue();
    }

    public ListProperty<Object> fieldProperty() {
        return field;
    }

    public void setField(ObservableList<Object> field) {
        this.field.set(field);
    }
}