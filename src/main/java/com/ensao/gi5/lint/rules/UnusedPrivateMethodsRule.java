package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.UnusedPrivateMethodsVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import java.util.ArrayList;
import java.util.List;
/**
 * This is a Java class called UnusedPrivateMethodsRule that extends the Rule class.
 * The "apply" method takes a "CompilationUnitWrapper" object and uses it to check for unused private methods
 * in a class using the "UnusedPrivateMethodsVisitor" visitor class.
 * The "UnusedPrivateMethodsVisitor" check the the private method , if it's not used anywhere it adds a new RuleWrapper object
 * to a list with the name of the method and the line number where it is located in the source code.
 * Then the "apply" methos check the list returned by the visitor if is not empty, new violation will be added
 * The isActive method is overriding the parent class method, returns always true, it means that this rule is always active.
 * */
public class UnusedPrivateMethodsRule extends Rule{
    public UnusedPrivateMethodsRule() {
        super(Constantes.LINT_REG_017, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
            List<RuleWrapper> unsedMethods = new ArrayList<>();
            compilationUnit.accept(new UnusedPrivateMethodsVisitor(unsedMethods), null);
            if(unsedMethods.size()!=0){
                for (RuleWrapper method : unsedMethods) {

                    Violation violation = ViolationMaker.makeViolation(
                            compilationUnit.getFileName(),
                            "Unused Private Methods",
                            method.getLine());
                    addViolation(violation);
                    }

            }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
