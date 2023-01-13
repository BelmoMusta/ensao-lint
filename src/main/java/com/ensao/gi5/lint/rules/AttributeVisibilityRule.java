package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

public class AttributeVisibilityRule extends Rule{
    public AttributeVisibilityRule() {
        super(Constantes.LINT_REG_013,Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.getTypes().forEach(type ->
                type.getFields().forEach(field -> {
                    if (field.getModifiers().isEmpty()) {
                        Violation violance = new Violation();
                        violance.setDescription("Attribut " + field.getVariables().get(0).getNameAsString() + " in class " + type.getNameAsString() + " does not have a visibility declared.");
                        violance.setFileName(compilationUnit.getFileName());
                        violance.setLine(field.getBegin().get().line);
                        addViolation(violance);
                    }
                })
        );
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
