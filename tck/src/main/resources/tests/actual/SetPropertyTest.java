package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleSetProperty;

public class SetPropertyTest {
    @FxProperty
    SetProperty<Object> field = new SimpleSetProperty<>(this, "field");
}