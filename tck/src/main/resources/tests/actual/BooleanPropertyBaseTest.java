package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.SimpleBooleanProperty;

public class BooleanPropertyBaseTest {
    @FxProperty
    BooleanPropertyBase field = new SimpleBooleanProperty(this, "field");
}