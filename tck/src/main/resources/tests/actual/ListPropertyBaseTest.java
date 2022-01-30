package org.fxproperty.tck;

import javafx.beans.property.ListPropertyBase;
import javafx.beans.property.SimpleListProperty;
import org.fxproperty.api.FxProperty;

public class ListPropertyBaseTest {
    @FxProperty
    ListPropertyBase<Object> field = new SimpleListProperty<>(this, "field");
}