package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 This class extends the {@link Rule} class and checks for violations of the rule that states boolean expressions should not have more than 2 operands.
 The rule is identified by the constant {@link Constantes#LINT_REG_006} and has the highest level of severity.
 The {@link #apply(CompilationUnitWrapper)} method uses the visitor pattern to traverse the abstract syntax tree of the compilation unit and checks for logical operators (&& and ||) with more than 2 operands.
 If a violation is found, an instance of {@link Violation} is created with the appropriate description, file name, and line number and is added to the list of violations.
 The {@link #isActive()} method is overridden to always return true, indicating that this rule is always active.
 */
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
