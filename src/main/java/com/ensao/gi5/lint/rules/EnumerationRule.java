package com.ensao.gi5.lint.rules;
import java.util.LinkedHashSet;
import java.util.Set;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.EnumerationVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.EnumerationWrapper;


public class EnumerationRule extends Rule {
    public EnumerationRule() {
        super(Constantes.LINT_REG_007, Level.LOW);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        Set<EnumerationWrapper> EnumElement = new LinkedHashSet<>();
        compilationUnit.accept(new EnumerationVisitor(), EnumElement);
        for (EnumerationWrapper EnumerationWrapper : EnumElement){
            if (!EnumerationWrapper.getName().matches("^[A-Z0-9_]*")){
                Violation violation = new Violation();
                violation.setFileName(compilationUnit.getFileName());
                violation.setDescription("Enum Elements should be all uppercase and should have an  underscore as sparators");
                violation.setLine(EnumerationWrapper.getLine());
                addViolation(violation);
            }
        }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}