package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.enumeration.Level;
import com.ensao.gi5.lint.visitor.BooleanExpressionVisitor;
import com.ensao.gi5.lint.wrapper.BooleanExpressionWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.ArrayList;
import java.util.List;

public class BooleanExpressionRule extends Rule {

    public BooleanExpressionRule() {
        super(Constantes.LINT_REG_006, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<BooleanExpressionWrapper> booleanExpressions = new ArrayList<>();
        compilationUnit.accept(new BooleanExpressionVisitor(), booleanExpressions);
        for (BooleanExpressionWrapper booleanExpression: booleanExpressions) {
            if (checkIfBooleanExpressionHasMoreThanTwoOperands(booleanExpression)) {
                buildViolationThenAddToCollection(
                        booleanExpression.getLine(),
                        "Boolean Expression " + booleanExpression + " should have 2 operands at most",
                        compilationUnit.getFileName()
                );
            }
        }
    }

    public boolean checkIfBooleanExpressionHasMoreThanTwoOperands(BooleanExpressionWrapper booleanWrapper) {
        String[] booleanOperands = booleanWrapper.getCondition().split("(&&) | (\\|\\|)");
        return booleanOperands.length > 2;
    }
    @Override
    public boolean isActive() {
        return true;
    }
}
