package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ConstantsInUpperCaseVisitors;

import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import com.ensao.gi5.lint.wrapper.VariableWrapper;

public class ConstantsInUpperCaseRule extends Rule {
	public ConstantsInUpperCaseRule() {
		super(Constantes.LINT_REG_005, Level.MEDIUM);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		 final Set<VariableWrapper> fieldNames = new LinkedHashSet<>();
			compilationUnit.accept(new ConstantsInUpperCaseVisitors(), fieldNames);
			for (VariableWrapper fieldName : fieldNames) {
		            if (!fieldName.getFieldName().matches("^[A-Z_]*")) {
		                final Violation violation = new Violation();		                
		                violation.setDescription("constant name must be in  upper case and concatenated with (_) '" + fieldName.getFieldName()+ "'");
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
