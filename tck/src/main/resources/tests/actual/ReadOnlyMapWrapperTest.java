package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyMapWrapper;

public class ReadOnlyMapWrapperTest {
    @FxProperty
    ReadOnlyMapWrapper<Object, Object> field = new ReadOnlyMapWrapper<>(this, "field");
}