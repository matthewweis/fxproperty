import org.fxproperty.javac.FxPropertyProcessor;

open module org.fxproperty.javac {
    requires org.fxproperty.api;
    requires transitive jdk.compiler;

    exports org.fxproperty.javac;

    uses javax.annotation.processing.Processor;
    provides javax.annotation.processing.Processor with FxPropertyProcessor;
}