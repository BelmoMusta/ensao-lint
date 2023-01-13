package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;

import com.ensao.gi5.lint.visitor.NumberOfMethodsVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import com.ensao.gi5.lint.wrapper.VariableWrapper;

public class NumberOfMethodsRule extends Rule {

	public NumberOfMethodsRule() {
		super(Constantes.LINT_REG_011, Level.HIGHEST);		
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final List<VariableWrapper> vars = new ArrayList<>();
		compilationUnit.accept(new NumberOfMethodsVisitors(), vars);
		vars.forEach(p->{			
            final Violation violation = new Violation();		                
            violation.setDescription("this class "+ p.getFieldName()+" has more than 20 method" );
            violation.setFileName(compilationUnit.getFileName());
            violation.setLine(p.getLine());
            addViolation(violation);  
 });
		
	}

	@Override
	public boolean isActive() {		
		return true;
	}

}