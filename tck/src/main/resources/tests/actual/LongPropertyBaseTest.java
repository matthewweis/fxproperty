package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.LongPropertyBase;
import javafx.beans.property.SimpleLongProperty;

public class LongPropertyBaseTest {
    @FxProperty
    LongPropertyBase field = new SimpleLongProperty(this, "field");
}