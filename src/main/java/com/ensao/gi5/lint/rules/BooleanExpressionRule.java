package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.visitor.BooleanExpressionVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.NameWrapper;

public class BooleanExpressionRule extends Rule {
 
	public BooleanExpressionRule() {
		super(Constantes.LINT_REG_006, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		
		List<NameWrapper> booleanExprs = new ArrayList<>();
		compilationUnit.accept(new BooleanExpressionVisitor(), booleanExprs);
		
		booleanExprs.forEach( t -> {
			String description = t.name() + " must have at most two operands"; 
			Violation violation = Utils.createNewInstanceOfViolation(description, compilationUnit.getFileName(), t.line());
			addViolation(violation); 	
		});
		
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
