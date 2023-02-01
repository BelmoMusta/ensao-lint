package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TypeVisitor extends VoidVisitorAdapter<List<ClassOrInterfaceDeclaration>> {

	@Override
	public void visit(ClassOrInterfaceDeclaration n,List<ClassOrInterfaceDeclaration>arg) {
		arg.add(n);
		super.visit(n, arg);
	}
}
