package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class BooleanExpressionOperandRule extends Rule{
    public BooleanExpressionOperandRule() {
        super(Constantes.LINT_REG_006, Level.HIGHEST);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(BinaryExpr n, Void arg) {
                if (isLogicalOperator(n.getOperator())) {
                    if (countOperands(n) > 2) {
                        final Violation violation = new Violation();
                        violation.setDescription("Boolean expression should not have more than 2 operands");
                        violation.setFileName(compilationUnit.getFileName());
                        violation.setLine(n.getBegin().get().line);
                        addViolation(violation);
                    }
                }
                super.visit(n, arg);
            }

            private boolean isLogicalOperator(BinaryExpr.Operator operator) {
                return operator == BinaryExpr.Operator.OR || operator == BinaryExpr.Operator.AND;
            }

            private int countOperands(BinaryExpr expr) {
                int count = 0;
                if (expr.getLeft() instanceof BinaryExpr) {
                    count += countOperands((BinaryExpr) expr.getLeft());
                } else {
                    count++;
                }
                if (expr.getRight() instanceof BinaryExpr) {
                    count += countOperands((BinaryExpr) expr.getRight());
                } else {
                    count++;
                }
                return count;
            }
        }, null);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
