package com.ensao.gi5.lint.rules;

import java.util.ArrayList;

import java.util.List;


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
		 final List<VariableWrapper> fieldNames = new ArrayList<>();
			compilationUnit.accept(new EnumerationVisitors(), fieldNames);
			 fieldNames.forEach(p ->  {
		            if (!p.getFieldName().matches("[A-Z]*")) {
		                final Violation violation = new Violation();		                
		                violation.setDescription("elements of the enumeration must be in  uppercase '" + p.getFieldName()+ "'");
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
