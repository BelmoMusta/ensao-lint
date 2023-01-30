package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.MethodVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.MethodWrapper;

public class ThrowReturnMethodRule extends Rule {
	
	public ThrowReturnMethodRule() {
		super(Constantes.LINT_REG_014, Level.LOW);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final List<MethodWrapper> throwAndReturn = new ArrayList<>();
		compilationUnit.accept(new MethodVisitors(), throwAndReturn);
		throwAndReturn.forEach(p->{
			 if (p.getReturnCount() > 1) {
	                final Violation violation = new Violation();		                
	                violation.setDescription("number of return statments must not exceed 1 statement" );
	                violation.setFileName(compilationUnit.getFileName());
	                violation.setLine(p.getLine());
	                addViolation(violation);
	            }
			 if (p.getThrowCount() > 1) {
	                final Violation violation = new Violation();		                
	                violation.setDescription("number of throw statments must not exceed 1 statement" );
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
