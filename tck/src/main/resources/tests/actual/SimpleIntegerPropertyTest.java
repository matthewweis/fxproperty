package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SimpleIntegerPropertyTest {
    @FxProperty
    SimpleIntegerProperty field = new SimpleIntegerProperty(this, "field");
}