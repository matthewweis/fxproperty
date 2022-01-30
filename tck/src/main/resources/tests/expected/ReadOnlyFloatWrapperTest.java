package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyFloatWrapper;

public class ReadOnlyFloatWrapperTest {
    @FxProperty
    ReadOnlyFloatWrapper field = new ReadOnlyFloatWrapper(this, "field");

    public Float getField() {
        return field.getValue();
    }

    public ReadOnlyFloatWrapper fieldProperty() {
        return field;
    }

    public void setField(Float field) {
        this.field.set(field);
    }
}