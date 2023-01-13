package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.LowerCaseVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.LowerCaseWrapper;
public class LowerCaseRule extends Rule {
    public LowerCaseRule() {
        super(Constantes.LINT_REG_003, Level.HIGH);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnitWrapper) {
        final Set<LowerCaseWrapper> fieldNames = new LinkedHashSet<>();
        compilationUnitWrapper.accept(new LowerCaseVisitors(), fieldNames);
        for (LowerCaseWrapper fieldName : fieldNames) {
            if (!fieldName.getFieldName().matches("^[\t*a-z].*")) {
                final Violation violation = new Violation();
                violation.setDescription("variable name starts with upper case '" + fieldName.getFieldName()+ "'");
                violation.setFileName(compilationUnitWrapper.getFileName());
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
