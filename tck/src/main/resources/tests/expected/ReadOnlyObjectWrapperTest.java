package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class ReadOnlyObjectWrapperTest {
    @FxProperty
    ReadOnlyObjectWrapper<Object> field = new ReadOnlyObjectWrapper<>(this, "field");

    public Object getField() {
        return field.getValue();
    }

    public ReadOnlyObjectWrapper<Object> fieldProperty() {
        return field;
    }

    public void setField(Object field) {
        this.field.set(field);
    }
}