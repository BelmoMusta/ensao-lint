package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;

import com.ensao.gi5.lint.visitor.AttributeStartByLowerCaseVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import com.ensao.gi5.lint.wrapper.AttributeStartByLowerCaseWrapper;


public class AttributeStartByLowerCase extends Rule {
	
	public AttributeStartByLowerCase() {
		super(Constantes.LINT_REG_003, Level.HIGH);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
        final Set<AttributeStartByLowerCaseWrapper> fieldNames = new LinkedHashSet<>();
		compilationUnit.accept(new AttributeStartByLowerCaseVisitor(), fieldNames);
		for (AttributeStartByLowerCaseWrapper fieldName : fieldNames) {
	            if (fieldName.getFieldName().matches("^[A-Z].*")) {
	                final Violation violation = new Violation();
	                violation.setDescription("attribute name starts with upper case '" + fieldName.getFieldName() + "'");
	                violation.setFileName(compilationUnit.getFileName());
	                violation.setLine(fieldName.getLine());
	                addViolation(violation);
	            }
	    }
		
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
