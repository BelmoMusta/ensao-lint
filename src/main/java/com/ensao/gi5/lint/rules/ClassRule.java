package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.visitor.ClassVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.classe.ClassWrapper;

public class ClassRule extends Rule{

	public ClassRule() {
		super(Constantes.LINT_REG_002, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		
        List<ClassWrapper> classes = new ArrayList<>();
        compilationUnit.accept(new ClassVisitor(), classes);

	}

	@Override
	public boolean isActive() {
		return true;
	}

}
