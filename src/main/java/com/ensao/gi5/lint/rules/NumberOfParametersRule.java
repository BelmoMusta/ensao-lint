package com.ensao.gi5.lint.rules;

import java.util.LinkedList;
import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.ConstructorVisitor;
import com.ensao.gi5.lint.visitor.MethodVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

public class NumberOfParametersRule extends Rule{

	public NumberOfParametersRule() {
		super(Constantes.LINT_REG_012, Level.HIGHEST);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		List<MethodDeclaration>mList=new LinkedList<>();
		List<ConstructorDeclaration>cList=new LinkedList<>();
		compilationUnit.accept(new MethodVisitor(), mList);
		compilationUnit.accept(new ConstructorVisitor(), cList);
		mList.forEach(m->{
			if(m.getParameters().size()>2) {
				final Violation violation=ViolationFactory.createViolation(getId(), m.getNameAsString(), compilationUnit.getFileName(), m.getBegin()
						.map(p->p.line)
						.orElse(-1)
						);
				addViolation(violation);
					
			}
		});
		cList.forEach(c->{
			if(c.getParameters().size()>2) {
				final Violation violation=ViolationFactory.createViolation(getId(), c.getNameAsString(), compilationUnit.getFileName(), c.getBegin()
						.map(p->p.line)
						.orElse(-1)
						);
				addViolation(violation);
			}
		});
	}

	@Override
	public boolean isActive() {
		return true;
	}
	

}
