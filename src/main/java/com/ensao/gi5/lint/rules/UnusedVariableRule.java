package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.NameExpr;

import java.util.List;
import java.util.stream.Collectors;

public class UnusedVariableRule extends  Rule{
    public UnusedVariableRule() {
        super(Constantes.LINT_REG_016, Level.MEDIUM);
      ;
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<VariableDeclarator> unusedVariables = compilationUnit.getCompilationUnit().findAll(VariableDeclarator.class)
                .stream()
                .filter(variable -> variable.getInitializer().isPresent() && variable.getInitializer().get().findAll(NameExpr.class, nameExpr -> nameExpr.getNameAsString().equals(variable.getNameAsString())).isEmpty())
                .collect(Collectors.toList());
        unusedVariables.forEach(variable -> {
            final Violation violation = new Violation();
            violation.setDescription("Les variables non utilisées sont supprimer: " + variable.getNameAsString());
            violation.setFileName(compilationUnit.getFileName());
            addViolation(violation);
        });
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
