package org.fxproperty.javac;

import com.sun.source.util.Trees;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.TransTypes;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

// use annotation processor to collect symbols, then use them during next phase
@SupportedAnnotationTypes("*") // see todo in getSupportedAnnotationTypes() method
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class FxPropertyProcessor extends AbstractProcessor {

    private Trees trees;
    private TreeTranslator visitor;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        this.processingEnv = processingEnvironment;
        this.trees = Trees.instance(processingEnvironment);

        final Context context = ((JavacProcessingEnvironment) processingEnvironment).getContext();
        final TreeMaker treeMaker = TreeMaker.instance(context);
        final Names names = Names.instance(context);
        final Types types = Types.instance(context);
        final JavacTypes typeUtils = JavacTypes.instance(context);
        final JavacElements elementUtils = JavacElements.instance(context);
        final Symtab symtab = Symtab.instance(context);
        final TransTypes transTypes = TransTypes.instance(context);

        visitor = new PropertyTreeTranslator(typeUtils, elementUtils, types, names, treeMaker, symtab, transTypes);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        if (!roundEnvironment.processingOver()) {
            roundEnvironment.getRootElements().forEach(element -> {
                final JCTree tree = (JCTree) trees.getTree(element);
                tree.accept(visitor);
            });
        }
        return false;
    }

}
