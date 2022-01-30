package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.ObservableList;

public class ReadOnlyListWrapperTest {
    @FxProperty
    ReadOnlyListWrapper<Object> field = new ReadOnlyListWrapper<>(this, "field");

    public ObservableList<Object> getField() {
        return field.get();
    }

    public ReadOnlyListWrapper<Object> fieldProperty() {
        return field;
    }

    public void setField(ObservableList<Object> field) {
        this.field.set(field);
    }
}