package com.ensao.gi5.lint.rules;
import java.util.LinkedHashSet;
import java.util.Set;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ConstantsVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ConstantsWrapper;


public class ConstantsRule extends Rule {
    public ConstantsRule() {
        super(Constantes.LINT_REG_005, Level.MEDIUM);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        Set<ConstantsWrapper> Constants = new LinkedHashSet<>();
        compilationUnit.accept(new ConstantsVisitor(), Constants);
        for (ConstantsWrapper ConstantsWrapper : Constants){
            if (!ConstantsWrapper.getName().matches("^[A-Z]*") || ConstantsWrapper.getName().contains("_") ) {
                Violation violation = new Violation();
                violation.setFileName(compilationUnit.getFileName());
                violation.setDescription(" Class constants should start with uppercase and should not contain underscores");
                violation.setLine(ConstantsWrapper.getLine());
                addViolation(violation);
            }
        }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}