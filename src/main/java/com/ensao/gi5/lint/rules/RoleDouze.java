package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.RoleDouzeVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RoleDouzeWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class RoleDouze extends Rule{
    public RoleDouze() {
        super(Constantes.LINT_REG_012, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<RoleDouzeWrapper> roleDouzeWrappers = new LinkedHashSet<>();
        compilationUnit.accept(new RoleDouzeVisitor(), roleDouzeWrappers);
        for (RoleDouzeWrapper roleDouzeWrapper : roleDouzeWrappers){
            if(roleDouzeWrapper.getCount() > 2){
                Violation violation = new Violation();
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(roleDouzeWrapper.getLigne());
                violation.setDescription("Le nombre de paramètres de la méthode est supérieur à 2");
                addViolation(violation);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
