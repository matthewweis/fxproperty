package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleMapProperty;

public class SimpleMapPropertyTest {
    @FxProperty
    SimpleMapProperty<Object, Object> field = new SimpleMapProperty<>(this, "field");
}