package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class ReadOnlyObjectWrapperTest {
    @FxProperty
    ReadOnlyObjectWrapper<Object> field = new ReadOnlyObjectWrapper<>(this, "field");
}