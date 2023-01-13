package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.RoleHuitVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RoleHuitWrapper;
import com.github.javaparser.ast.stmt.BlockStmt;

import java.util.LinkedHashSet;
import java.util.Set;

public class RoleHuit extends Rule{


    public RoleHuit() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        // le corps d'une méthode ne dépasse pas 30 lignes
        Set<RoleHuitWrapper> roleHuitWrappers = new LinkedHashSet<>();
        compilationUnit.accept(new RoleHuitVisitor(), roleHuitWrappers);
        for (RoleHuitWrapper roleHuitWrapper : roleHuitWrappers) {
            if (roleHuitWrapper.getLigne() > 30) {
                Violation violation = new Violation();
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(roleHuitWrapper.getLigne());
                violation.setDescription("Le corps de la méthode " + roleHuitWrapper.getNom() + " dépasse 30 lignes");
                addViolation(violation);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
