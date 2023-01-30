package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;

public class IfElseRule extends Rule{
    public IfElseRule() {
        super(Constantes.LINT_REG_018, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.getMethods().forEach(method ->
                method.getBody().ifPresent(body ->
                        body.getStatements().forEach(statement -> {
                            if (statement instanceof IfStmt) {
                                IfStmt ifStmt = (IfStmt) statement;
                                if (ifStmt.getThenStmt() == null || !ifStmt.getElseStmt().isPresent()) {
                                    Violation violance = new Violation();
                                    violance.setDescription("If or Else statement in method " + method.getNameAsString() + " is missing curly braces.");
                                    violance.setFileName(compilationUnit.getFileName());
                                    violance.setLine(ifStmt.getBegin().get().line);
                                    addViolation(violance);
                                }
                            }
                        })
                )
        );
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
