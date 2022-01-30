package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StringPropertyTest {
    @FxProperty
    StringProperty field = new SimpleStringProperty(this, "field");

    public String getField() {
        return field.getValue();
    }

    public StringProperty fieldProperty() {
        return field;
    }

    public void setField(String field) {
        this.field.set(field);
    }
}