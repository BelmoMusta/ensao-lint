package com.ensao.gi5.lint.rules;


import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.expr.LambdaExpr;

import java.util.List;

public class LambdaReferenceMethodRule extends Rule{
    public LambdaReferenceMethodRule() {
        super(Constantes.LINT_REG_010, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        // Récupération des expressions lambda
        final List<LambdaExpr> lambdaExprs = compilationUnit.getLambdaExpressions();
        // Parcours de chaque expression lambda
        for (LambdaExpr lambdaExpr : lambdaExprs) {
                final Violation violation = new Violation();
                violation.setDescription("l'expression lambda : " + lambdaExpr + ", peut etre remplacer la method reference");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(lambdaExpr.getBegin().get().line);
                // Ajouter l'objet Violation à la liste des violations
                addViolation(violation);
            }
    }


    @Override
    public boolean isActive() {
        return true;
    }
}
