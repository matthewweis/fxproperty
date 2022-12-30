package org.fxproperty.api;

/*
 * =====================================================================
 * https://wiki.openjdk.org/display/OpenJFX/JavaFX+Property+Architecture
 * =====================================================================
 * Basic
 * Basic Half-Lazy
 * Basic Half-Lazy With Default Value
 * Basic Lazy
 * Basic Lazy With Default Value
 * Basic Reacting To Changes
 * Reflection
 * Reflection Half-Lazy
 * Reflection Half-Lazy With Default Value
 * Reflection Lazy
 * Reflection Lazy With Default Value
 * Reflection Reacting To Changes
 * Read-Only
 * Computed
 */
public enum InstantiationStrategy {
    BASIC,
    BASIC_HALF_LAZY,
    BASIC_HALF_LAZY_WITH_DEFAULT_VALUE,
    BASIC_LAZY,
    BASIC_LAZY_WITH_DEFAULT_VALUE,
    BASIC_REACTING_TO_CHANGES,
    REFLECTION,
    REFLECTION_HALF_LAZY,
    REFLECTION_HALF_LAZY_WITH_DEFAULT_VALUE,
    REFLECTION_LAZY,
    REFLECTION_LAZY_WITH_DEFAULT_VALUE,
    REFLECTION_REACTING_TO_CHANGES,
    READ_ONLY,
    COMPUTED
}
