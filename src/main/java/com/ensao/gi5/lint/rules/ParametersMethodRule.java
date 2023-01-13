package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ConstructorVisitors;
import com.ensao.gi5.lint.visitor.MethodVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ConstructorWrapper;
import com.ensao.gi5.lint.wrapper.MethodWrapper;

public class ParametersMethodRule extends Rule{

	
	public ParametersMethodRule() {
		super(Constantes.LINT_REG_012, Level.HIGHEST);		
	}
	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final List<MethodWrapper> parametersCount = new ArrayList<>();
		compilationUnit.accept(new MethodVisitors(), parametersCount);
		 parametersCount.forEach(p->{
			 if (p.getParameterCount() > 2) {
	                final Violation violation = new Violation();		                
	                violation.setDescription("number of parameters of the method must not exceed 2 parameters" );
	                violation.setFileName(compilationUnit.getFileName());
	                violation.setLine(p.getLine());
	                addViolation(violation);
	            }
		 });
		 final List<ConstructorWrapper> parametersConstructorCount = new ArrayList<>();
			compilationUnit.accept(new ConstructorVisitors(), parametersConstructorCount);
			parametersConstructorCount.forEach(p->{
				 if (p.getConstructParamCount() > 2) {
		                final Violation violation = new Violation();		                
		                violation.setDescription("number of parameters of the constructor must not exceed 2 parameters" );
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
