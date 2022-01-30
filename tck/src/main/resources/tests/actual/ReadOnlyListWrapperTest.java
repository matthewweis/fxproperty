package org.fxproperty.tck;

import org.fxproperty.api.FxProperty;
import javafx.beans.property.ReadOnlyListWrapper;

public class ReadOnlyListWrapperTest {
    @FxProperty
    ReadOnlyListWrapper<Object> field = new ReadOnlyListWrapper<>(this, "field");
}