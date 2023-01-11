package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.NamingVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.NamingWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class NamingRule extends Rule{
    public NamingRule() {
        super(Constantes.LINT_REG_002, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        final Set<NamingWrapper> namingWrapperSet = new LinkedHashSet<>();
        compilationUnit.accept(new NamingVisitor(),namingWrapperSet);

        for (NamingWrapper namingWrapper: namingWrapperSet) {
            String name = namingWrapper.getName();
            if(!Character.isUpperCase(name.charAt(0)) || name.contains("_")){
                final Violation violation = new Violation();
                violation.setDescription(namingWrapper.getType()+"'"+namingWrapper.getName()+"' Does not start with Capital letter or it contains '_'" );
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(namingWrapper.getLine());
                addViolation(violation);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
