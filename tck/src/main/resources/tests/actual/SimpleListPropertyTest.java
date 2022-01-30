package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleListProperty;

public class SimpleListPropertyTest {
    @FxProperty
    SimpleListProperty<Object> field = new SimpleListProperty<>(this, "field");
}