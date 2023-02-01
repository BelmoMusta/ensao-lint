package com.ensao.gi5.lint.rules;

import java.util.LinkedList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.TypeVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

public class NumberOfMethodsRule extends Rule {

	public NumberOfMethodsRule() {
		super(Constantes.LINT_REG_011, Level.HIGHEST);
	}
	
	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		List<ClassOrInterfaceDeclaration> list=new LinkedList<>();
		compilationUnit.accept(new TypeVisitor(), list);
		list.forEach(e->{
			if(e.getMethods().size()>20) {
				final Violation violation =ViolationFactory.createViolation(getId(),e.getNameAsString(), compilationUnit.getFileName(),e.getBegin()
						.map(p->p.line)
						.orElse(-1));
				addViolation(violation);
			}
		});
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
