package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

public class LongPropertyTest {
    @FxProperty
    LongProperty field = new SimpleLongProperty(this, "field");
}