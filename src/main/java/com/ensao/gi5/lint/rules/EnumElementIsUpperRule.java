package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;

import com.ensao.gi5.lint.visitor.EnumerationVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.VariableWrapper;

public class EnumElementIsUpperRule extends Rule {
	public EnumElementIsUpperRule() {
		super(Constantes.LINT_REG_007, Level.LOW);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		 final Set<VariableWrapper> fieldNames = new LinkedHashSet<>();
			compilationUnit.accept(new EnumerationVisitors(), fieldNames);
			for (VariableWrapper fieldName : fieldNames) {
		            if (!fieldName.getFieldName().matches("[A-Z]*")) {
		                final Violation violation = new Violation();		                
		                violation.setDescription("elements of the enumeration must be in  uppercase '" + fieldName.getFieldName()+ "'");
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
