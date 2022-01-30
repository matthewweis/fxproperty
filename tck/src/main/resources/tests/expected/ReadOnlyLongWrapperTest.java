package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyLongWrapper;

public class ReadOnlyLongWrapperTest {
    @FxProperty
    ReadOnlyLongWrapper field = new ReadOnlyLongWrapper(this, "field");

    public Long getField() {
        return field.getValue();
    }

    public ReadOnlyLongWrapper fieldProperty() {
        return field;
    }

    public void setField(Long field) {
        this.field.set(field);
    }
}