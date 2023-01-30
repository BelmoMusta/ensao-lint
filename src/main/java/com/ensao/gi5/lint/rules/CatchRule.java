package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.CatchVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.StatementWrapper;

public class CatchRule  extends Rule{
	
	public CatchRule() {
		super(Constantes.LINT_REG_015, Level.LOW);
	}
	
	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final List<StatementWrapper> statementCatch = new ArrayList<>();
		compilationUnit.accept(new CatchVisitors(), statementCatch);
	
		
		statementCatch.forEach(p->{	
					if( !p.getStatement().toString().contains("log")) {
	                final Violation violation = new Violation();		                
	                violation.setDescription("is better to add logger expression in this catch statement "+ p.getStatement() );
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
