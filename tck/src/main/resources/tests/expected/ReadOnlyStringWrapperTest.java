package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyStringWrapper;

public class ReadOnlyStringWrapperTest {
    @FxProperty
    ReadOnlyStringWrapper field = new ReadOnlyStringWrapper(this, "field");

    public String getField() {
        return field.getValue();
    }

    public ReadOnlyStringWrapper fieldProperty() {
        return field;
    }

    public void setField(String field) {
        this.field.set(field);
    }
}