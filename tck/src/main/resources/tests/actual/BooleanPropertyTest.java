package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class BooleanPropertyTest {
    @FxProperty
    BooleanProperty field = new SimpleBooleanProperty(this, "field");
}