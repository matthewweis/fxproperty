package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ReadOnlyIntegerPropertyTest {
    @FxProperty
    ReadOnlyIntegerProperty field = new SimpleIntegerProperty(this, "field");

    public Integer getField() {
        return field.getValue();
    }

    public ReadOnlyIntegerProperty fieldProperty() {
        return field;
    }

}