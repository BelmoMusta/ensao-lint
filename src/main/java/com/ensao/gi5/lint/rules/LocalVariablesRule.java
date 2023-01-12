package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LocalVariablesRule extends Rule {
    public LocalVariablesRule() {
        super(Constantes.LINT_REG_003, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(VariableDeclarator n, Void arg) {
                if (!Character.isLowerCase(n.getNameAsString().charAt(0))) {
                    final Violation violation = new Violation();
                    violation.setDescription("Local variable should start with a lowercase letter");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(n.getBegin().get().line);
                    addViolation(violation);
                }
                super.visit(n, arg);
            }
        }, null);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
