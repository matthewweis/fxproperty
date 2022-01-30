package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ReadOnlyBooleanPropertyTest {
    @FxProperty
    ReadOnlyBooleanProperty field = new SimpleBooleanProperty(this, "field");
}