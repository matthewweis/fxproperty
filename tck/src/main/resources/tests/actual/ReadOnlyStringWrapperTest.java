package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyStringWrapper;

public class ReadOnlyStringWrapperTest {
    @FxProperty
    ReadOnlyStringWrapper field = new ReadOnlyStringWrapper(this, "field");
}