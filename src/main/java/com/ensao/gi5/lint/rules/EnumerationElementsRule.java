package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

public class EnumerationElementsRule extends Rule{

	protected EnumerationElementsRule() {
		super(Constantes.LINT_REG_007,Level.LOW);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

}
