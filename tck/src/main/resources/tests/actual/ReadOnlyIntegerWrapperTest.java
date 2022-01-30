package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;

public class ReadOnlyIntegerWrapperTest {
    @FxProperty
    ReadOnlyIntegerWrapper field = new ReadOnlyIntegerWrapper(this, "field");
}