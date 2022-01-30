package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyFloatWrapper;

public class ReadOnlyFloatWrapperTest {
    @FxProperty
    ReadOnlyFloatWrapper field = new ReadOnlyFloatWrapper(this, "field");
}