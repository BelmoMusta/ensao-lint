package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ruleTroisVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ruleTroisWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class ruleTrois extends Rule {

	public  ruleTrois() {
        super(Constantes.LINT_REG_003, Level.HIGHEST);

	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		// TODO Auto-generated method stub
	      Set<ruleTroisWrapper> roleTroisWrappers = new LinkedHashSet<>();
	        compilationUnit.accept(new ruleTroisVisitor(), roleTroisWrappers);
	        for (ruleTroisWrapper roleTroisWrapper : roleTroisWrappers){
	            if(!roleTroisWrapper.getNom().matches("^[\t*a-z].*")){
	                Violation violation = new Violation();
	                violation.setFileName(compilationUnit.getFileName());
	                violation.setLine(roleTroisWrapper.getLigne());
	                violation.setDescription("Le nom de la variable locale "+roleTroisWrapper.getNom() +" doit commencer par une lettre minuscule !!");
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
