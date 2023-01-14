package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.*;

/*
    - This class is used to implement "rule number 6" which guaranteed that any boolean expression should not have more than 2 operands.
    - This class needs to extend the "Rule" class so that we can instantiate a new rule which is rule number 6.
    - To implement the rule, we created the method "apply" which iterates over each statement of each method in the specified file's compilation unit and
      checks if a statement has more than two operands. If so, it creates a new instance of "Violation" with a description so that we can increment the
      number of violations observed.
    - To guarantee that the rule is always active we need to override the method "isActive" that should always return true to indicate that the rule is active.
*/

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
