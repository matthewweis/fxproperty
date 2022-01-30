package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;

public class MapPropertyTest {
    @FxProperty
    MapProperty<Object, Object> field = new SimpleMapProperty<>(this, "field");
}