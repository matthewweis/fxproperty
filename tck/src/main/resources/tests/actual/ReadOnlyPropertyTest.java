package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ReadOnlyPropertyTest {
    @FxProperty
    ReadOnlyProperty<Object> field = new SimpleObjectProperty<>(this, "field");
}