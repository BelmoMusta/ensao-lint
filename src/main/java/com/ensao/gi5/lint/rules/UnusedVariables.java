package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.body.VariableDeclarator;

import java.util.HashSet;
import java.util.Set;

public class UnusedVariables extends Rule{
    public UnusedVariables() {
        super(Constantes.LINT_REG_016, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        final Set<String> usedVariables = new HashSet<>();
        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(NameExpr n, Void arg) {
                usedVariables.add(n.getNameAsString());
                super.visit(n, arg);
            }

            @Override
            public void visit(VariableDeclarator n, Void arg) {
                if (!usedVariables.contains(n.getNameAsString())) {
                    final Violation violation = new Violation();
                    violation.setDescription("Unused variable");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(n.getBegin().get().line);
                    addViolation(violation);
                }
                super.visit(n, arg);
            }
        }, null);
    }


    @Override
    public boolean isActive() {
        return true;
    }
}
