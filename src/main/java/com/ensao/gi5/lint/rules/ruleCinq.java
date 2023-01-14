package com.ensao.gi5.lint.rules;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ruleCinqVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ruleCinqWrapper;

import java.util.LinkedHashSet;
import java.util.Set;
public class ruleCinq extends Rule {

	public  ruleCinq() {
		 super(Constantes.LINT_REG_005, Level.MEDIUM);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		// TODO Auto-generated method stub
		  Set<ruleCinqWrapper> roleCinqWrappers = new LinkedHashSet<>();
	        compilationUnit.accept(new ruleCinqVisitor(), roleCinqWrappers);
	        for (ruleCinqWrapper roleCinqWrapper : roleCinqWrappers) {
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
		// TODO Auto-generated method stub
		return true;
	}

}
