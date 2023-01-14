package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.TypeDeclaration;

/*
    - This class is used to implement "rule number 6" which ensures that the name of any class, interface, annotation or enum must always start with a capital
      letter and without "_" between words.
    - This class needs to extend the "Rule" class so that we can instantiate a new rule which is rule number 2.
    - To implement the rule, we created the method "apply" which iterates over each type declaration in the specified file's compilation unit and checks if a
      type does not start with a capital letter or contains any "_". If so, it creates a new instance of "Violation" with a description so that we can increment
      the number of violations observed.
    - To guarantee that the rule is always active we need to override the method "isActive" that should always return true to indicate that the rule is active.
*/

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
