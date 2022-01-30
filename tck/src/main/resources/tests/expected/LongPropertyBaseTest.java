package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.*;

public class LongPropertyBaseTest {
    @FxProperty
    LongPropertyBase field = new SimpleLongProperty(this, "field");

    public Long getField() {
        return field.getValue();
    }

    public LongPropertyBase fieldProperty() {
        return field;
    }

    public void setField(Long field) {
        this.field.set(field);
    }
}