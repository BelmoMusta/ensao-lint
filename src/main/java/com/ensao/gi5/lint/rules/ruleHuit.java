package com.ensao.gi5.lint.rules;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ruleHuitVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ruleHuitWrapper;
import com.github.javaparser.ast.stmt.BlockStmt;

import java.util.LinkedHashSet;
import java.util.Set;

public class ruleHuit extends Rule {

	public ruleHuit() {
		super(Constantes.LINT_REG_008, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		// TODO Auto-generated method stub
		
        Set<ruleHuitWrapper> roleHuitWrappers = new LinkedHashSet<>();
        compilationUnit.accept(new ruleHuitVisitor(), roleHuitWrappers);
        for (ruleHuitWrapper roleHuitWrapper : roleHuitWrappers) {
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
		// TODO Auto-generated method stub
		return true;
	}

}
