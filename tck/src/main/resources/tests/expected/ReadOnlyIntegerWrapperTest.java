package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;

public class ReadOnlyIntegerWrapperTest {
    @FxProperty
    ReadOnlyIntegerWrapper field = new ReadOnlyIntegerWrapper(this, "field");

    public Integer getField() {
        return field.getValue();
    }

    public ReadOnlyIntegerWrapper fieldProperty() {
        return field;
    }

    public void setField(Integer field) {
        this.field.set(field);
    }
}