package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.LocalVariableVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import java.util.ArrayList;
import java.util.List;

public class LocalVariablesRule extends Rule {
    public LocalVariablesRule() {
        super(Constantes.LINT_REG_003, Level.HIGH);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try{
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
        catch (Exception e){
            System.out.println();
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
