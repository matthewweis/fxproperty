package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class PropertyTest {
    @FxProperty
    Property<Object> field = new SimpleObjectProperty<Object>(this, "field");
}