package com.ensao.gi5.lint.rules;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ruleQuatreVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ruleQuatreWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class ruleQuatre extends Rule {

	 public ruleQuatre() {
		 super(Constantes.LINT_REG_004, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		// TODO Auto-generated method stub
		  Set<ruleQuatreWrapper> roleTroisWrappers = new LinkedHashSet<>();
	        compilationUnit.accept(new ruleQuatreVisitor(), roleTroisWrappers);
	        for (ruleQuatreWrapper roleTroisWrapper : roleTroisWrappers) {
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
		// TODO Auto-generated method stub
		return false;
	}

}
