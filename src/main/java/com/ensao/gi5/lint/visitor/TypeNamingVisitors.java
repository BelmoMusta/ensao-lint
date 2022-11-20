package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.ensao.gi5.lint.wrapper.TypeNameWrapper;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TypeNamingVisitors extends VoidVisitorAdapter<List<TypeNameWrapper>> {

	@Override
	public void visit(AnnotationDeclaration n, List<TypeNameWrapper> arg) {
		arg.add(new TypeNameWrapper(n));
		super.visit(n, arg);
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration n, List<TypeNameWrapper> arg) {
		arg.add(new TypeNameWrapper(n)); 
		super.visit(n, arg);
	}

	@Override
	public void visit(EnumDeclaration n, List<TypeNameWrapper> arg) {
		arg.add(new TypeNameWrapper(n)); 
		super.visit(n, arg);
	}

	
	
	

}
