package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyLongProperty;
import javafx.beans.property.SimpleLongProperty;

public class ReadOnlyLongPropertyTest {
    @FxProperty
    ReadOnlyLongProperty field = new SimpleLongProperty(this, "field");

    public Long getField() {
        return field.getValue();
    }

    public ReadOnlyLongProperty fieldProperty() {
        return field;
    }

}