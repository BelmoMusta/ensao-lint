package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.MethodLengthVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleCountWrapper;
import java.util.ArrayList;
import java.util.List;
/**
 * This is a Java class called MethodLengthRule that extends the Rule class.
 * The "apply" method takes a "CompilationUnitWrapper" object and uses it to check for methods length using the "MethodLengthVisitor" visitor class.
 * The "MethodLengthVisitor" check if the length of each method, if the length > 30 it adds a new RuleWrapper object to a list with the name of the variable
 * and the line number where it is located in the source code.
 * Then the "apply" methos check the list returned by the visitor if is not empty, new violation will be added
 * The isActive method is overriding the parent class method, returns always true, it means that this rule is always active.
 * */
public class MethodLengthRule extends Rule {
    public MethodLengthRule() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
            List<RuleCountWrapper> methods = new ArrayList<>();
            compilationUnit.accept(new MethodLengthVisitor(methods),null);
            if(methods.size() != 0){
                for (RuleCountWrapper method:methods
                     ) {
                    Violation violation = ViolationMaker.makeViolation(
                            compilationUnit.getFileName(),
                            "Method body exceed 30 lines ",
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

