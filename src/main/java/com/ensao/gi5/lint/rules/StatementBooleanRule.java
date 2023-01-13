package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;

import com.ensao.gi5.lint.visitor.StatementBooleanVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import com.ensao.gi5.lint.wrapper.StatementWrapper;

public class StatementBooleanRule extends Rule{
	
	public StatementBooleanRule() {
		super(Constantes.LINT_REG_006, Level.HIGHEST);
	}
	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final List<StatementWrapper> statementBooleanWrappers = new ArrayList<>();
		compilationUnit.accept(new StatementBooleanVisitors(), statementBooleanWrappers);
		
		statementBooleanWrappers.forEach(p->{
			 if (p.getStatement().split("&&|\\|\\|").length > 2) {
	                final Violation violation = new Violation();		                
	                violation.setDescription("boolean expression must not exceed two opearands" );
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
