package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyLongWrapper;

public class ReadOnlyLongWrapperTest {
    @FxProperty
    ReadOnlyLongWrapper field = new ReadOnlyLongWrapper(this, "field");
}