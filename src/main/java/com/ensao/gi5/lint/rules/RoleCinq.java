package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.RoleCinqVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RoleCinqWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class RoleCinq extends Rule{

    public RoleCinq() {
        super(Constantes.LINT_REG_005, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<RoleCinqWrapper> roleCinqWrappers = new LinkedHashSet<>();
        compilationUnit.accept(new RoleCinqVisitor(), roleCinqWrappers);
        for (RoleCinqWrapper roleCinqWrapper : roleCinqWrappers) {
            if (!roleCinqWrapper.getNom().matches("^[A-Z]*") || roleCinqWrapper.getNom().contains("_") ) {
                Violation violation = new Violation();
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(roleCinqWrapper.getLigne());
                violation.setDescription("Le nom de la variable " + roleCinqWrapper.getNom() + " ne respecte pas la convention de nommage");
                addViolation(violation);

            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
