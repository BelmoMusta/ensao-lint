package com.ensao.gi5.lint.rules;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ruleDeuzeVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ruleDeuzeWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class ruleDeuze extends Rule{

	public ruleDeuze() {
		  super(Constantes.LINT_REG_012, Level.HIGHEST);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		// TODO Auto-generated method stub
		 Set<ruleDeuzeWrapper> roleDouzeWrappers = new LinkedHashSet<>();
	        compilationUnit.accept(new ruleDeuzeVisitor(), roleDouzeWrappers);
	        for (ruleDeuzeWrapper roleDouzeWrapper : roleDouzeWrappers){
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
		// TODO Auto-generated method stub
		return false;
	}

}
