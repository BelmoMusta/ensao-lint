package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.LowerCaseVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.LowerCaseWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class AttributesRule extends Rule{
    public AttributesRule(){
        super(Constantes.LINT_REG_004, Level.HIGH);
    }

    public void apply(CompilationUnitWrapper compilationUnit) {
        final Set<LowerCaseWrapper> fieldNames = new LinkedHashSet<>();
        compilationUnit.accept(new LowerCaseVisitors(), fieldNames);
        for (LowerCaseWrapper fieldName : fieldNames) {
            if (!fieldName.getFieldName().matches("^[a-z].*")) {
                final Violation violation = new Violation();
                violation.setDescription("attribute name must start with lower case '" + fieldName.getFieldName() + "'");
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
