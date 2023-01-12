package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class IfElseRule extends Rule{
    public IfElseRule() {
        super(Constantes.LINT_REG_018, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(IfStmt n, Void arg) {
                if (!n.getThenStmt().isBlockStmt()) {
                    final Violation violation = new Violation();
                    violation.setDescription("If statement should have curly braces");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(n.getThenStmt().getBegin().get().line);
                    addViolation(violation);
                }
                if (n.getElseStmt().isPresent() && !n.getElseStmt().get().isBlockStmt()) {
                    final Violation violation = new Violation();
                    violation.setDescription("Else statement should have curly braces");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(n.getElseStmt().get().getBegin().get().line);
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
