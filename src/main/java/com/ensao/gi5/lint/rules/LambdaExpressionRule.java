package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.IfElseVisitor;
import com.ensao.gi5.lint.visitor.LambdaExpressionVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.GeneralStatementWrapper;
import com.github.javaparser.ast.expr.LambdaExpr;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *The LambdaExpressionRule class is a Java class that extends the Rule abstract class.
 *  It is used to check for the use of lambda expressions in a
 *  compilation unit and return a violation if any are found.
 *
 *The apply method is an implementation of the abstract method in the Rule class.
 * It uses the LambdaExpressionVisitor class to traverse the compilation unit
 * and collect all lambda expressions in a set. Then, for each lambda expression found,
 * it creates a violation using the ViolationFactory class, sets the violation message,
 * and the line where the lambda expression is found. Finally, it adds the violation to
 * the violations list using the addViolation method.
 *
 * This class is a rule that checks the use of lambda expressions in a compilation unit.
 * It will return a violation if any lambda expressions are found.
 *
 * **/
public class LambdaExpressionRule extends Rule{
    public LambdaExpressionRule() {
        super(Constantes.LINT_REG_010, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<LambdaExpr> lambdaExprList = new LinkedHashSet<>();
        compilationUnit.accept(new LambdaExpressionVisitor(),lambdaExprList);

        for(LambdaExpr lambdaExpr: lambdaExprList){

                final Violation violation = ViolationFactory.ViolationFactory(
                        compilationUnit.getFileName(),
                        "'"+lambdaExpr+"' is intuitive , must be replaced",
                        lambdaExpr.getBegin().get().line);
                addViolation(violation);

        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
