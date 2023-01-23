package com.ensao.gi5.lint.rules;

import java.util.List;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

public class NamingRule extends Rule {

	public NamingRule() {
		super(Constantes.LINT_REG_002, Level.HIGHEST);
		// TODO Auto-generated constructor stub
	}

	   @Override
	    public void apply(CompilationUnitWrapper compilationUnit) {
		    final List<String> types = compilationUnit.getTypes();

	        for (String typeName : types) {
	            if (!Character.isUpperCase(typeName.charAt(0))) {
	                Violation violation = new Violation();
	                violation.setDescription("Type '" + typeName + "' does not start with an uppercase letter");
	                violation.setFileName(compilationUnit.getFileName());
	                addViolation(violation);
	            }
	            if(typeName.contains("_")) {
	                Violation violation = new Violation();
	                violation.setDescription("Type '" + typeName + "' contains an underscore");
	                violation.setFileName(compilationUnit.getFileName());
	                addViolation(violation);
	            }
	        }
	    }

	    @Override
	    public boolean isActive() {
	        return true;
	    }

}
