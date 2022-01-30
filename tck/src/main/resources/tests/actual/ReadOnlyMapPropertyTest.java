package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyMapProperty;
import javafx.beans.property.SimpleMapProperty;

public class ReadOnlyMapPropertyTest {
    @FxProperty
    ReadOnlyMapProperty<Object,Object> field = new SimpleMapProperty<>(this, "field");
}