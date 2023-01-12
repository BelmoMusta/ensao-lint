package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.MethodLengthVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleCountWrapper;
import java.util.ArrayList;
import java.util.List;

public class MethodLengthRule extends Rule {
    public MethodLengthRule() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try {
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
        } catch (Exception e) {
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}

