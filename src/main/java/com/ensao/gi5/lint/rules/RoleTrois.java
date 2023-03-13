package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.RoleTroisVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RoleTroisWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class RoleTrois extends Rule{


    public RoleTrois() {
        super(Constantes.LINT_REG_003, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<RoleTroisWrapper> roleTroisWrappers = new LinkedHashSet<>();
        compilationUnit.accept(new RoleTroisVisitor(), roleTroisWrappers);
        for (RoleTroisWrapper roleTroisWrapper : roleTroisWrappers){
            if(!roleTroisWrapper.getNom().matches("^[\t*a-z].*")){
                Violation violation = new Violation();
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(roleTroisWrapper.getLigne());
                violation.setDescription("Le nom de la variable locale "+roleTroisWrapper.getNom() +" doit commencer par une lettre minuscule");
                addViolation(violation);
            }
        }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
