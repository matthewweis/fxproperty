package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringPropertyBase;

public class StringPropertyBaseTest {
    @FxProperty
    StringPropertyBase field = new SimpleStringProperty(this, "field");

    public String getField() {
        return field.getValue();
    }

    public StringPropertyBase fieldProperty() {
        return field;
    }

    public void setField(String field) {
        this.field.set(field);
    }
}