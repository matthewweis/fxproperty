package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleStringProperty;

public class SimpleStringPropertyTest {
    @FxProperty
    SimpleStringProperty field = new SimpleStringProperty(this, "field");

    public String getField() {
        return field.getValue();
    }

    public SimpleStringProperty fieldProperty() {
        return field;
    }

    public void setField(String field) {
        this.field.set(field);
    }
}