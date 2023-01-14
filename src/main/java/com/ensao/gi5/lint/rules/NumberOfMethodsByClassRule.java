package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.ClassMethodsVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleCountWrapper;
import java.util.ArrayList;
import java.util.List;
/**
 * This is a Java class called NumberOfMethodsByClassRule that extends the Rule class.
 * The "apply" method takes a "CompilationUnitWrapper" object and uses it to check for number of methods
 * in a class using the "ClassMethodsVisitor" visitor class.
 * The "ClassMethodsVisitor" check the number of methods in a class , if there are more than 20 methods
 * it adds a new RuleWrapper object to a list with the name of the method and the line number where it is located in the source code.
 * Then the "apply" methos check the list returned by the visitor if is not empty, new violation will be added
 * The isActive method is overriding the parent class method, returns always true, it means that this rule is always active.
 * */
public class NumberOfMethodsByClassRule extends Rule {
    public NumberOfMethodsByClassRule() {
        super(Constantes.LINT_REG_011, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
            List<RuleCountWrapper> countMethods = new ArrayList<>();
            compilationUnit.accept(new ClassMethodsVisitor(countMethods),null);
            for (RuleCountWrapper nb: countMethods
                 ) {
                Violation violation = ViolationMaker.makeViolation(
                        compilationUnit.getFileName(),
                        "Number of methods exceeds 20",
                        nb.getLine());
                addViolation(violation);
            }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}