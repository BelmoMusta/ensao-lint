package com.ensao.gi5.lint.visitor;

import java.util.Set;

import com.ensao.gi5.lint.wrapper.ElementWrapper;
import com.ensao.gi5.lint.wrapper.TypeNameWrapper;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;

public class TypeNameVisitor extends ElementVisitor {

	@Override
	public void visit(ClassOrInterfaceDeclaration n, Set<ElementWrapper> typeNameWrappers) {
		typeNameWrappers.add(new TypeNameWrapper(n));
		super.visit(n, typeNameWrappers);
	}

	@Override
	public void visit(AnnotationDeclaration n, Set<ElementWrapper> typeNameWrappers) {
		typeNameWrappers.add(new TypeNameWrapper(n));
		super.visit(n, typeNameWrappers);
	}

	@Override
	public void visit(EnumDeclaration n, Set<ElementWrapper> typeNameWrappers) {
		typeNameWrappers.add(new TypeNameWrapper(n));
		super.visit(n, typeNameWrappers);
	}

}
