package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.VariableDeclarator;

public class LocalVariableSyntaxRule extends  Rule{
    public LocalVariableSyntaxRule() {
        super(Constantes.LINT_REG_003, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.getCompilationUnit().findAll(VariableDeclarator.class).forEach(variableDeclarator -> {
            String name = variableDeclarator.getNameAsString();
            int lineNumber = variableDeclarator.getName().getBegin().get().line;
            if (!Character.isLowerCase(name.charAt(0))) {
                final Violation violation = new Violation();
                violation.setDescription("Les variables locales commencent par une minuscule: " + name);
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(lineNumber);
                addViolation(violation);
            }
        });

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
