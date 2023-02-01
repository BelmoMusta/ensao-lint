package com.ensao.gi5.lint.visitor;

import java.util.Set;

import com.ensao.gi5.lint.wrapper.ElementWrapper;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TypeNameVisitor extends VoidVisitorAdapter<Set<ElementWrapper>> {

	@Override
	public void visit(ClassOrInterfaceDeclaration n, Set<ElementWrapper> arg) {
		arg.add(new ElementWrapper(n.getNameAsString(), n.getBegin().map(p -> p.line).orElse(-1)));
		super.visit(n, arg);
	}

	@Override
	public void visit(AnnotationDeclaration n, Set<ElementWrapper> arg) {
		arg.add(new ElementWrapper(n.getNameAsString(), n.getBegin().map(p -> p.line).orElse(-1)));
		super.visit(n, arg);
	}

	@Override
	public void visit(EnumDeclaration n, Set<ElementWrapper> arg) {
		arg.add(new ElementWrapper(n.getNameAsString(), n.getBegin().map(p -> p.line).orElse(-1)));
		super.visit(n, arg);
	}

}
