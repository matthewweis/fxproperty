package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class RawPropertyTest {
    @FxProperty
    Property field = new SimpleObjectProperty(this, "field");

}