package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;

public class ClearVisibilityOfClasses extends Rule{

    public ClearVisibilityOfClasses() {
        super(Constantes.LINT_REG_013,Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        for (ClassOrInterfaceDeclaration classDeclaration : compilationUnit.getClasses()) {
            for (FieldDeclaration field : classDeclaration.getFields()) {
                System.out.println(field.getModifiers());
                if (field.getModifiers().isEmpty()) {
                    Violation vio = new Violation();
                    vio.setDescription("Field " + field.getVariables().get(0).getNameAsString() + " in class " + classDeclaration.getNameAsString() + " does not have a visibility declaration.");
                    vio.setFileName(compilationUnit.getFileName());
                    vio.setLine(field.getBegin().get().line);
                    addViolation(vio);
                }
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
