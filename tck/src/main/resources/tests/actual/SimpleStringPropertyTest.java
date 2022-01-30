package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.SimpleStringProperty;

public class SimpleStringPropertyTest {
    @FxProperty
    SimpleStringProperty field = new SimpleStringProperty(this, "field");
}