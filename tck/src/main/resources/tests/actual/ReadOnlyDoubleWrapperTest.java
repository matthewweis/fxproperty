package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;

public class ReadOnlyDoubleWrapperTest {
    @FxProperty
    ReadOnlyDoubleWrapper field = new ReadOnlyDoubleWrapper(this, "field");
}