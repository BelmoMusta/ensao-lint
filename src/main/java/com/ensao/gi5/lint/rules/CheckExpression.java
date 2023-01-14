package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.*;

public class CheckExpression extends Rule {
    public CheckExpression() {super(Constantes.LINT_REG_006,Level.HIGHEST);}

    public void apply(CompilationUnitWrapper compilationUnit) {
        for (MethodDeclaration method : compilationUnit.getMethods()) {
            for (Statement statement : method.getBody().get().getStatements()) {
                if (statement instanceof IfStmt) {
                    IfStmt ifStmt = (IfStmt) statement;
                    if (ifStmt.getCondition().findAll(BinaryExpr.class).size() >= 2) {
                        Violation vio = new Violation();
                        vio.setDescription("If statement in method " + method.getNameAsString() + " has more than 2 logical operands.");
                        vio.setFileName(compilationUnit.getFileName());
                        vio.setLine(ifStmt.getBegin().get().line);
                        addViolation(vio);
                    }
                }
            }
        }
    }

    @Override
    public boolean isActive() {return true;};

}
