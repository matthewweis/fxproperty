package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;

public class ReadOnlyDoubleWrapperTest {
    @FxProperty
    ReadOnlyDoubleWrapper field = new ReadOnlyDoubleWrapper(this, "field");

    public Double getField() {
        return field.getValue();
    }

    public ReadOnlyDoubleWrapper fieldProperty() {
        return field;
    }

    public void setField(Double field) {
        this.field.set(field);
    }
}