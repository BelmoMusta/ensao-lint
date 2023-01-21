package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;

public class ClassVariableNameRule extends Rule {
    public ClassVariableNameRule() {
        super(Constantes.LINT_REG_004, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.getCompilationUnit().findAll(FieldDeclaration.class).forEach(fieldDeclaration -> {
            String name = fieldDeclaration.getVariables().get(0).getNameAsString();
            int lineNumber = fieldDeclaration.getVariables().get(0).getBegin().get().line;
            if (!Character.isLowerCase(name.charAt(0))) {
                final Violation violation = new Violation();
                violation.setDescription("Les attributs d'une classe commencent par une minuscule: " + name);
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
