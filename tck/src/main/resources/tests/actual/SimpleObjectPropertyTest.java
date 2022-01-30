package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleObjectProperty;

public class SimpleObjectPropertyTest {
    @FxProperty
    SimpleObjectProperty<Object> field = new SimpleObjectProperty(this, "field");
}