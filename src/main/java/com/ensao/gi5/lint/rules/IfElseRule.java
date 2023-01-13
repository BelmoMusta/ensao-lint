package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.IfElseVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.StatementWrapper;

public class IfElseRule  extends Rule{
	
	public IfElseRule() {
		super(Constantes.LINT_REG_018, Level.LOW);
	}
	
	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final List<StatementWrapper> ifElse = new ArrayList<>();
		compilationUnit.accept(new IfElseVisitors(), ifElse);	
		
		ifElse.forEach(p->{
				if(!p.getStatement().contains("{") && !p.getStatement().contains("}")) {
	                final Violation violation = new Violation();		                
	                violation.setDescription("if/Else expression should have ({ }) "+ p.getStatement() );
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