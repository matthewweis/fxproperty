package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

public class ListPropertyTest {
    @FxProperty
    ListProperty<Object> field = new SimpleListProperty<>(this, "field");
}