package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;

import com.ensao.gi5.lint.visitor.VariableStartByLowerCaseVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import com.ensao.gi5.lint.wrapper.VariableStartByLowerCaseWrapper;


public class VariableStartByLowerCase extends Rule {
	
	public VariableStartByLowerCase() {
		super(Constantes.LINT_REG_003, Level.HIGH);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
        final Set<VariableStartByLowerCaseWrapper> fieldNames = new LinkedHashSet<>();
		compilationUnit.accept(new VariableStartByLowerCaseVisitor(), fieldNames);
		for (VariableStartByLowerCaseWrapper fieldName : fieldNames) {
	            if (fieldName.getFieldName().matches("^[A-Z].*")) {
	                final Violation violation = new Violation();
	                violation.setDescription("variable name starts with upper case '" + fieldName.getFieldName() + "'");
	                violation.setFileName(compilationUnit.getFileName());
	                violation.setLine(2);
	                addViolation(violation);
	            }
	    }
		
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
