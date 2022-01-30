package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlySetWrapper;

public class ReadOnlySetWrapperTest {
    @FxProperty
    ReadOnlySetWrapper<Object> field = new ReadOnlySetWrapper<>(this, "field");
}