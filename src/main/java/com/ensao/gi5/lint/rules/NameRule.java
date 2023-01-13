package com.ensao.gi5.lint.rules;
import java.util.LinkedHashSet;
import java.util.Set;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.NameWrapper;

public class NameRule extends Rule {
    public NameRule() {
        super(Constantes.LINT_REG_002, Level.HIGH);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        final Set<NameWrapper> NameWrapperSet = new LinkedHashSet<>();
        for (NameWrapper NameWrapper: NameWrapperSet) {
            String typeName = NameWrapper.getName();
            if (!Character.isUpperCase(typeName.charAt(0)) || typeName.contains("_")){
                final Violation violation = new Violation();
                violation.setDescription(" Java types should start with uppercase and should not contain underscores");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(NameWrapper.getLine());
                addViolation(violation);
            }
            }
        }
    @Override
    public boolean isActive() {
        return true;
    }
}