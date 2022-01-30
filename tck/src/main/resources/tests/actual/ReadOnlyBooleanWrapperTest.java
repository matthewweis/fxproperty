package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;

public class ReadOnlyBooleanWrapperTest {
    @FxProperty
    ReadOnlyBooleanWrapper field = new ReadOnlyBooleanWrapper(this, "field");
}