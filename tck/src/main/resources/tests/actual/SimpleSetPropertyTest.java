package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleSetProperty;

public class SimpleSetPropertyTest {
    @FxProperty
    SimpleSetProperty<Object> field = new SimpleSetProperty<>(this, "field");
}