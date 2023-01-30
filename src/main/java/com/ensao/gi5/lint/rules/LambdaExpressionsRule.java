package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;

import java.util.List;


public class LambdaExpressionsRule extends Rule{
    public LambdaExpressionsRule() {
        super(Constantes.LINT_REG_010, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        final List<LambdaExpr> lambdaExprs = compilationUnit.getLambdaExpressions();
        lambdaExprs.forEach(lambdaExpr -> {
            final Violation violation = new Violation();
            violation.setDescription("Lambda expresssion " + lambdaExpr + " can be replaced by method reference.");
            violation.setFileName(compilationUnit.getFileName());
            violation.setLine(lambdaExpr.getBegin().get().line);
            addViolation(violation);
        });
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
