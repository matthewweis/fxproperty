package org.fxproperty.api;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
public @interface FxProperty {

    /**
     * Visibility of the generated Property method.
     */
    Visibility property() default Visibility.PUBLIC;

    /**
     * Visibility of the generated final Property value setter method.
     */
    Visibility setter() default Visibility.PUBLIC;

    /**
     * Visibility of the generated final Property value getter method.
     */
    Visibility getter() default Visibility.PUBLIC;

    // todo see: http://what-when-how.com/javafx-2/understanding-the-javafx-beans-convention-properties-and-bindings-part-1/
    //      allow options for lazy strategy, half-lazy strategy

}









