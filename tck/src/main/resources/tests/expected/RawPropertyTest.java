package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class RawPropertyTest {
    @FxProperty
    Property field = new SimpleObjectProperty(this, "field");

    public Object getField() {
        return field.getValue();
    }

    public Property fieldProperty() {
        return field;
    }

    public void setField(Object field) {
        this.field.setValue(field);
    }
    
}