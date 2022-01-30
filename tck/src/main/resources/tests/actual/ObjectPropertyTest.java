package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ObjectPropertyTest {
    @FxProperty
    ObjectProperty<Object> field = new SimpleObjectProperty<>(this, "field");
}