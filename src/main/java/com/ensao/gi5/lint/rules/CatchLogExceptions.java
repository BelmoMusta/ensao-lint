package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class CatchLogExceptions extends Rule{
    public CatchLogExceptions() {
        super(Constantes.LINT_REG_015, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(CatchClause n, Void arg) {
                if (!n.getBody().getStatements().stream()
                        .anyMatch(s -> s instanceof ExpressionStmt && ((ExpressionStmt) s)
                                .getExpression() instanceof MethodCallExpr && ((MethodCallExpr)((ExpressionStmt) s)
                                .getExpression()).getName().asString().equals("log"))) {
                    final Violation violation = new Violation();
                    violation.setDescription("Catch block does not contain log statement");
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