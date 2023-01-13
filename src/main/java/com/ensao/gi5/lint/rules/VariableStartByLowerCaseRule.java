package com.ensao.gi5.lint.rules;

import java.util.ArrayList;

import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.VariableStartByLowerCaseVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.VariableStartByLowerCaseWrapper;


public class VariableStartByLowerCaseRule extends Rule {
	public VariableStartByLowerCaseRule() {
		super(Constantes.LINT_REG_003, Level.HIGH);
	}
	@Override
	public void apply(CompilationUnitWrapper compilationUnitWrapper) {
            final List<VariableStartByLowerCaseWrapper> fieldNames = new ArrayList<>();
			compilationUnitWrapper.accept(new VariableStartByLowerCaseVisitors(), fieldNames);
		    fieldNames.forEach( p ->  {
		            if (!p.getFieldName().matches("^[a-z].*")) {
		                final Violation violation = new Violation();		                
		                violation.setDescription("variable name starts with upper case '" + p.getFieldName()+ "'");
		                violation.setFileName(compilationUnitWrapper.getFileName());
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
