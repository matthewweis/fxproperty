package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.*;

public class MapPropertyBaseTest {
    @FxProperty
    MapPropertyBase<Object, Object> field = new SimpleMapProperty<>(this, "field");
}