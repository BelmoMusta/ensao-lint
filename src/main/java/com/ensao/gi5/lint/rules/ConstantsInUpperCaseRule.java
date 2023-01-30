package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;


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
		 final List<VariableWrapper> fieldNames = new ArrayList<>();
			compilationUnit.accept(new ConstantsInUpperCaseVisitors(), fieldNames);
			fieldNames.forEach ( p ->  {
		            if (!p.getFieldName().matches("^[A-Z_]*")) {
		                final Violation violation = new Violation();		                
		                violation.setDescription("constant name must be in  upper case and concatenated with (_) '" + p.getFieldName()+ "'");
		                violation.setFileName(compilationUnit.getFileName());
		                violation.setLine(p.getLine());
		                addViolation(violation);
		            }
		    });
		
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
