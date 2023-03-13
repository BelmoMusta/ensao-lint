package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.RoleQuatreVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RoleQuatreWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class RoleQuatre extends Rule{
    public RoleQuatre() {
        super(Constantes.LINT_REG_004, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<RoleQuatreWrapper> roleTroisWrappers = new LinkedHashSet<>();
        compilationUnit.accept(new RoleQuatreVisitor(), roleTroisWrappers);
        for (RoleQuatreWrapper roleTroisWrapper : roleTroisWrappers) {
            if (!roleTroisWrapper.getNom().matches(".*[a-z].*")) {
                Violation violation = new Violation();
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(roleTroisWrapper.getLigne());
                violation.setDescription("Le nom de la variable " + roleTroisWrapper.getNom() + " ne respecte pas la convention de nommage");
                addViolation(violation);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
