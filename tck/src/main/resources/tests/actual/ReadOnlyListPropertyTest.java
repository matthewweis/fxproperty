package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;

public class ReadOnlyListPropertyTest {
    @FxProperty
    ReadOnlyListProperty<Object> field = new SimpleListProperty<>(this, "field");
}