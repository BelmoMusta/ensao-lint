package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;

import com.ensao.gi5.lint.visitor.AttributeStartByLowerCaseVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import com.ensao.gi5.lint.wrapper.AttributeStartByLowerCaseWrapper;


public class AttributeStartByLowerCaseRule extends Rule {
	
	public AttributeStartByLowerCaseRule() {
		super(Constantes.LINT_REG_004, Level.HIGH);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
        final Set<AttributeStartByLowerCaseWrapper> fieldNames = new LinkedHashSet<>();
		compilationUnit.accept(new AttributeStartByLowerCaseVisitors(), fieldNames);
		for (AttributeStartByLowerCaseWrapper fieldName : fieldNames) {
	            if (!fieldName.getFieldName().matches("^[a-z].*")) {
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