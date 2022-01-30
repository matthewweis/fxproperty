package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReadOnlyStringPropertyTest {
    @FxProperty
    ReadOnlyStringProperty field = new SimpleStringProperty(this, "field");

    public String getField() {
        return field.getValue();
    }

    public ReadOnlyStringProperty fieldProperty() {
        return field;
    }
}