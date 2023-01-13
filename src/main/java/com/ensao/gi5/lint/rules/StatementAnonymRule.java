package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.StatementAnonymVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.StatementWrapper;

public class StatementAnonymRule extends Rule{
	
	public StatementAnonymRule() {
		super(Constantes.LINT_REG_009, Level.HIGH);
	}
	
	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final List<StatementWrapper> statementAnonymWrappers = new ArrayList<>();
		compilationUnit.accept(new StatementAnonymVisitors(), statementAnonymWrappers);
	
		
		statementAnonymWrappers.forEach(p->{			
	                final Violation violation = new Violation();		                
	                violation.setDescription("replace this anonymous expression with lamnda expression "+ p.getStatement() );
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
