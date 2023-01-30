package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.VisibilityAttributeVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.VariableWrapper;

public class VisibilityAttributeRule extends Rule {

	public VisibilityAttributeRule() {
		super(Constantes.LINT_REG_013, Level.HIGHEST);		
	}
	
	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		final List<VariableWrapper> vars = new ArrayList<>();
		compilationUnit.accept(new VisibilityAttributeVisitors(), vars);
		vars.forEach(p -> {			
            final Violation violation = new Violation();		                
            violation.setDescription("this attribute "+ p.getFieldName()+" has not the access modifier" );
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
