package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ConstructorVisitor extends VoidVisitorAdapter<List<ConstructorDeclaration>>{

	@Override
	public void visit(ConstructorDeclaration cd,List<ConstructorDeclaration> arg) {
		arg.add(cd);
		super.visit(cd, arg);
	}
}
