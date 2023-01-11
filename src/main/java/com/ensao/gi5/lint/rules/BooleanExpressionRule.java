package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.enumeration.Level;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.BooleanExpressionVisitor;
import com.ensao.gi5.lint.wrapper.BooleanExpressionWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BooleanExpressionRule extends Rule {

    public BooleanExpressionRule() {
        super(Constantes.LINT_REG_006, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<BooleanExpressionWrapper> booleanExpressions = new ArrayList<>();
        compilationUnit.accept(new BooleanExpressionVisitor(), booleanExpressions);
        booleanExpressions = booleanExpressions
                .stream()
                .filter(this::checkIfBooleanExpressionHasMoreThanTwoOperands)
                .collect(Collectors.toList());
        for (BooleanExpressionWrapper booleanExpression: booleanExpressions) {
            Violation violation = violationBuilder
                    .withDescription("Boolean Expression " + booleanExpression + " should have 2 operands at most")
                    .withFileName(compilationUnit.getFileName())
                    .withLine(booleanExpression.getLine())
                    .build();
            addViolation(violation);
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
