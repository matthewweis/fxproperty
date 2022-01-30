package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlySetProperty;
import javafx.beans.property.SimpleSetProperty;

public class ReadOnlySetPropertyTest {
    @FxProperty
    ReadOnlySetProperty<Object> field = new SimpleSetProperty<>(this, "field");
}