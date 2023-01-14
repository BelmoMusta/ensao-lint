package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;

/*
    - This class is used to implement "rule number 13" which ensures that a class field must have an explicit visibility declaration.
    - This class needs to extend the "Rule" class so that we can instantiate a new rule which is rule number 13.
    - To implement the rule, we created the method "apply" which iterates over each class in the specified file's compilation unit and checks if a field is
      declared without a visibility. If so, it creates a new instance of "Violation" with a description so that we can increment the number of violations observed.
    - To guarantee that the rule is always active we need to override the method "isActive" that should always return true to indicate that the rule is active.
*/

public class ClearVisibilityOfClasses extends Rule{

    public ClearVisibilityOfClasses() {
        super(Constantes.LINT_REG_013,Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        for (ClassOrInterfaceDeclaration classDeclaration : compilationUnit.getClasses()) {
            for (FieldDeclaration field : classDeclaration.getFields()) {
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
