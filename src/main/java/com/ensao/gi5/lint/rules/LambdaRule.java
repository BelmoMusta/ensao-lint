package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.LambdaVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import java.util.ArrayList;
import java.util.List;

public class LambdaRule extends Rule {
    public LambdaRule() {
        super(Constantes.LINT_REG_010, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try {
            List<RuleWrapper> intuitiveLambdas = new ArrayList<>();
            compilationUnit.accept(new LambdaVisitor(intuitiveLambdas), null);
            if (intuitiveLambdas.size() != 0) {
                for (RuleWrapper lambda : intuitiveLambdas) {
                    Violation violation = ViolationMaker.makeViolation(
                            compilationUnit.getFileName(),
                            "Intuitive lambda expression found, consider using method reference instead.",
                            lambda.getLine());
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