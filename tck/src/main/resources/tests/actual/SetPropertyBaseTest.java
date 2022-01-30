package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SetPropertyBase;
import javafx.beans.property.SimpleSetProperty;

public class SetPropertyBaseTest {
    @FxProperty
    SetPropertyBase<Object> field = new SimpleSetProperty<>(this, "field");
}