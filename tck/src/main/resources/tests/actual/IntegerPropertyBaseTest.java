package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.IntegerPropertyBase;
import javafx.beans.property.SimpleIntegerProperty;

public class IntegerPropertyBaseTest {
    @FxProperty
    IntegerPropertyBase field = new SimpleIntegerProperty(this, "field");
}