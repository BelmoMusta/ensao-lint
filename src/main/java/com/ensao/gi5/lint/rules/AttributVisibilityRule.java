package com.ensao.gi5.lint.rules;

import java.util.Set;
import java.util.stream.Collectors;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.AttributWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import java.lang.reflect.Modifier;

public class AttributVisibilityRule extends Rule {

    public AttributVisibilityRule() {
        super(Constantes.LINT_REG_013, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        // Get all class attributes
        final Set<AttributWrapper> fields = compilationUnit.getFields()
                .stream()
                .filter(fieldDeclaration -> !(fieldDeclaration.isStatic() && fieldDeclaration.isFinal()))
                .map(fieldDeclaration -> new AttributWrapper(fieldDeclaration.getVariable(0).getName(), fieldDeclaration.getModifiers()))
                .collect(Collectors.toSet());

        // Iterate through attributes
        for (AttributWrapper field : fields) {
            // Verify visibility
            if (!field.getModifiers().contains(Modifier.PUBLIC) && !field.getModifiers().contains(Modifier.PRIVATE) && !field.getModifiers().contains(Modifier.PROTECTED)) {
                final Violation violation = new Violation();
                violation.setDescription("Attribut'" + field.getName() + "' visibility is not declared");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(field.getLine());

                addViolation(violation);
            }
        }
    }


    @Override
    public boolean isActive() {
        return true;
    }
}
