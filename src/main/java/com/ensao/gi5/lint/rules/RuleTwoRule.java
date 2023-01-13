package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.VariableVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.VariableWrapper;

public class RuleTwoRule extends Rule{
	public RuleTwoRule() {
		super(Constantes.LINT_REG_002,Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final List<VariableWrapper> vars = new ArrayList<>();
		compilationUnit.accept(new VariableVisitors(), vars);
		 vars.forEach(p->{
			 if (p.getFieldName().contains("_") || !p.getFieldName().matches("^[A-Z].*")) {
	                final Violation violation = new Violation();		                
	                violation.setDescription("name must start with uppercase and not contain (_)"+p.getFieldName() );
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
