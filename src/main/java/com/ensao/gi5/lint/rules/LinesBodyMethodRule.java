package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;

import com.ensao.gi5.lint.visitor.MethodVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.MethodWrapper;


public class LinesBodyMethodRule extends Rule {

	public LinesBodyMethodRule() {
		super(Constantes.LINT_REG_008, Level.HIGHEST);		
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final List<MethodWrapper> linesCount = new ArrayList<>();
		compilationUnit.accept(new MethodVisitors(), linesCount);
	    linesCount.forEach(p -> {
	            if (p.getLineCount() > 30) {
	                final Violation violation = new Violation();		                
	                violation.setDescription("number of lines must not exceed 30 lines" );
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
