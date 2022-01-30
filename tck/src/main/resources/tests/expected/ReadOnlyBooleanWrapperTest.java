package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;

public class ReadOnlyBooleanWrapperTest {
    @FxProperty
    ReadOnlyBooleanWrapper field = new ReadOnlyBooleanWrapper(this, "field");

    public Boolean getField() {
        return field.getValue();
    }

    public ReadOnlyBooleanWrapper fieldProperty() {
        return field;
    }

    public void setField(Boolean field) {
        this.field.set(field);
    }
}