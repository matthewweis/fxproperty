package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StringPropertyTest {
    @FxProperty
    StringProperty field = new SimpleStringProperty(this, "field");
}