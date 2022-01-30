package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class SimpleBooleanPropertyTest {
    @FxProperty
    SimpleBooleanProperty field = new SimpleBooleanProperty(this, "field");
}