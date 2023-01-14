package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.ReturnCountVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleCountWrapper;
import java.util.ArrayList;
import java.util.List;
/**
 * This is a Java class called ReturnCountRule that extends the Rule class.
 * The "apply" method takes a "CompilationUnitWrapper" object and uses it to check for number of returns by method
 * in a class using the "ReturnCountVisitor" visitor class.
 * The "ReturnCountVisitor" check the number of returns in a method , if there are more than 2 parameters
 * it adds a new RuleWrapper object to a list with the name of the method and the line number where it is located in the source code.
 * Then the "apply" methos check the list returned by the visitor if is not empty, new violation will be added
 * The isActive method is overriding the parent class method, returns always true, it means that this rule is always active.
 * */
public class ReturnCountRule extends Rule{
    public ReturnCountRule() {
        super(Constantes.LINT_REG_014, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
            List<RuleCountWrapper> methodReturnCounts = new ArrayList<>();
            compilationUnit.accept(new ReturnCountVisitor(methodReturnCounts),null);
            for (RuleCountWrapper methodReturnCount : methodReturnCounts) {
                if (methodReturnCount.getCount() > 1) {
                    Violation violation = ViolationMaker.makeViolation(
                            compilationUnit.getFileName(),
                            "number of returns exceeds 1",
                            methodReturnCount.getLine());
                    addViolation(violation);
                }
            }
    }
    @Override
    public boolean isActive() {
        return true;
    }
}
