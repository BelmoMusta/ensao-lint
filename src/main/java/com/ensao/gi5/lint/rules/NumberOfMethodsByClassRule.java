package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.ClassMethodsVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleCountWrapper;
import java.util.ArrayList;
import java.util.List;

public class NumberOfMethodsByClassRule extends Rule {
    public NumberOfMethodsByClassRule() {
        super(Constantes.LINT_REG_011, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try {
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
        } catch (Exception e) {
            // Handle exception
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}