package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.AnonymousVisitors;
import com.ensao.gi5.lint.visitor.ConstVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class AnonymousRule extends Rule{
    public AnonymousRule() {
        super(Constantes.LINT_REG_009, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        final Set<String> m = new LinkedHashSet<>();
        compilationUnit.accept(new AnonymousVisitors(), m);

        if(m.size()>0){
            final Violation violation = new Violation();
            violation.setDescription(m.size()+" Anonymous instanciation violation ");
            violation.setFileName(compilationUnit.getFileName());
            // violation.setLine(n.getLine());
            addViolation(violation);
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
