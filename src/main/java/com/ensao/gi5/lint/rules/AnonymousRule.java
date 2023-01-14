package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.AnonymousVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import java.util.Hashtable;

/**
 * AnonymousRule is a Java class that defines a rule for linting of Java code.
 * The rule checks for the use of anonymous inner classes in the code, and if found, generates violations.
 * The rule is enforced by the AnonymousVisitor, which uses a Hashtable to keep track of anonymous class instantiations.
 * The visitor has a Hashtable field to store information about anonymous class instantiations,
 * which it finds by checking for the presence of an anonymous class body in each ObjectCreationExpr node.
 * When an anonymous class body is found, the visitor adds the class body's string representation and the line number of the instantiation to the Hashtable.
 * The isActive method is overriding the parent class method, returns always true, it means that this rule is always active.
 */
public class AnonymousRule extends Rule{
    public AnonymousRule() {
        super(Constantes.LINT_REG_009, Level.HIGH);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

            Hashtable<String,Integer> anonymousInstanciations = new Hashtable<>();
            compilationUnit.accept(new AnonymousVisitor(anonymousInstanciations), null);
            if(anonymousInstanciations.size()!=0){
                for (String miss : anonymousInstanciations.keySet()) {
                    Violation violation=ViolationMaker.makeViolation(compilationUnit.getFileName()
                            ,"Anonymous inner class found please use a lambda expression instead."
                            , anonymousInstanciations.get(miss));
                    addViolation(violation);
                }
            }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
