package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class PropertyTest {
    @FxProperty
    Property<Object> field = new SimpleObjectProperty<>(this, "field");

    public Object getField() {
        return field.getValue();
    }

    public Property<Object> fieldProperty() {
        return field;
    }

    public void setField(Object field) {
        this.field.setValue(field);
    }
}