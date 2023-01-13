package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.TypeDeclaration;

public class TypeStartWIthMajWithoutUnderscore extends Rule{
    public TypeStartWIthMajWithoutUnderscore() {
        super(Constantes.LINT_REG_002,Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        for (TypeDeclaration<?> type : compilationUnit.getTypes()) {
            if (!Character.isUpperCase(type.getNameAsString().charAt(0)) || type.getNameAsString().contains("_")) {
                Violation vio = new Violation();
                vio.setDescription("Type " + type.getNameAsString() + " should start with an uppercase letter and should not contain underscores.");
                vio.setFileName(compilationUnit.getFileName());
                vio.setLine(type.getBegin().get().line);
                addViolation(vio);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
