package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleLongProperty;

public class SimpleLongPropertyTest {
    @FxProperty
    SimpleLongProperty field = new SimpleLongProperty(this, "field");
}