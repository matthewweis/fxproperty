package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ReadOnlyObjectPropertyTest {
    @FxProperty
    ReadOnlyObjectProperty<Object> field = new SimpleObjectProperty<>(this, "field");

    public Object getField() {
        return field.getValue();
    }

    public ReadOnlyObjectProperty<Object> fieldProperty() {
        return field;
    }
}