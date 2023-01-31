package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.EnumVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.LowUpperWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class EnumRule extends Rule {
    public EnumRule(){
        super(Constantes.LINT_REG_007, Level.LOW);

    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        final Set<LowUpperWrapper> fieldNames = new LinkedHashSet<>();
        compilationUnit.accept(new EnumVisitors(), fieldNames);
        for (LowUpperWrapper fieldName : fieldNames) {
            if (!fieldName.getFieldName().matches("[A-Z]*")) {
                final Violation violation = new Violation();
                violation.setDescription("elements of the enumeration must be in  uppercase '" + fieldName.getFieldName()+ "'");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(fieldName.getLine());
                addViolation(violation);
            }
        }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
