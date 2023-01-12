package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.ReturnCountVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleCountWrapper;
import java.util.ArrayList;
import java.util.List;

public class ReturnCountRule extends Rule{
    public ReturnCountRule() {
        super(Constantes.LINT_REG_014, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try {
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
        } catch (Exception e) {
        }
    }
    @Override
    public boolean isActive() {
        return true;
    }
}
