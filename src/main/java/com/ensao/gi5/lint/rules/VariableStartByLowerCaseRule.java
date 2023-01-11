package com.ensao.gi5.lint.rules;

import java.util.LinkedHashSet;
import java.util.Set;
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
            final Set<VariableStartByLowerCaseWrapper> fieldNames = new LinkedHashSet<>();
			compilationUnitWrapper.accept(new VariableStartByLowerCaseVisitors(), fieldNames);
			for (VariableStartByLowerCaseWrapper fieldName : fieldNames) {
		            if (!fieldName.getFieldName().toString().matches("^[\t*a-z].*")) {
		                final Violation violation = new Violation();		                
		                violation.setDescription("variable name starts with upper case '" + fieldName.getFieldName().toString()+ "'");
		                violation.setFileName(compilationUnitWrapper.getFileName());
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
