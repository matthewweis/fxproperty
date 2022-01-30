package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleObjectProperty;

public class ObjectPropertyBaseTest {
    @FxProperty
    ObjectPropertyBase<Object> field = new SimpleObjectProperty<>(this, "field");
}