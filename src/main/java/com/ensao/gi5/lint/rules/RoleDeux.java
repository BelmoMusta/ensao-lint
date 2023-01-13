package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.RoleDeuxVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RoleDeuxWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class RoleDeux extends Rule{
    public RoleDeux() {
        super(Constantes.LINT_REG_002, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set < RoleDeuxWrapper> roleDeuxWrappers = new LinkedHashSet<>();
        compilationUnit.accept(new RoleDeuxVisitor(), roleDeuxWrappers);
        for(RoleDeuxWrapper roleDeuxWrapper : roleDeuxWrappers){
            String name = roleDeuxWrapper.getNom();
            if(!Character.isUpperCase(name.charAt(0)) || name.contains("_")){
                Violation violation = new Violation();
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(roleDeuxWrapper.getLigne());
                violation.setDescription("Le nom de la " + roleDeuxWrapper.getType() + " " + name + " ne respecte pas la convention de nommage");
                addViolation(violation);
            }

        }

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
