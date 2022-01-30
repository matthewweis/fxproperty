package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class IntegerPropertyTest {
    @FxProperty
    IntegerProperty field = new SimpleIntegerProperty(this, "field");
}