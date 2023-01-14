package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.LocalVariableVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import java.util.ArrayList;
import java.util.List;
/**
 * This is a Java class called LocalVariablesRule that extends the Rule class.
 * The "apply" method takes a "CompilationUnitWrapper" object and uses it to check for variables naming using the "LocalVariableVisitor" visitor class.
 * The "LocalVariableVisitor" check if the variables starts with an lower case character , if its ths case it adds a new RuleWrapper object to a list with the name of the variable
 * and the line number where it is located in the source code.
 * Then the "apply" methos check the list returned by the visitor if is not empty, new violation will be added
 * The isActive method is overriding the parent class method, returns always true, it means that this rule is always active.
 * */
public class LocalVariablesRule extends Rule {
    public LocalVariablesRule() {
        super(Constantes.LINT_REG_003, Level.HIGH);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
            List<RuleWrapper> variables = new ArrayList<>();
            compilationUnit.accept(new LocalVariableVisitor(variables),null);
            if (variables.size() != 0) {
                for (RuleWrapper variable : variables) {
                    Violation violation = ViolationMaker.makeViolation(
                            compilationUnit.getFileName(),
                            "Local variable error",
                            variable.getLine());
                    addViolation(violation);
                }
            }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
