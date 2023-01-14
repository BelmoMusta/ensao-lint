package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

public class LineRule extends Rule{

	private final int MAX_LINE_COUNT = 30;
	
	public LineRule() {
		super(Constantes.LINT_REG_008, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		
		
		 for (MethodDeclaration method : compilationUnit.getMethods()) {
	            int lineCount = method.getEnd().get().line - method.getBegin().get().line;
	            if (lineCount > MAX_LINE_COUNT) {
	                Violation violation = new Violation();
	                violation.setDescription("Method body should not exceed 30 lines: " + method.getNameAsString());
	                violation.setFileName(compilationUnit.getFileName());
	                violation.setLine(method.getBegin().get().line);
	                addViolation(violation);
	            }
	        
	    }
	}
	
	@Override
	public boolean isActive() {
		return true;
	}
}
