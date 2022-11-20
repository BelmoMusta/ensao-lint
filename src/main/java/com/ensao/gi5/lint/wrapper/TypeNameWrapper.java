package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;

public class TypeNameWrapper {
	
	private final String name;
	private final int line; 
	
	public TypeNameWrapper(AnnotationDeclaration annotation) {
		this.name = annotation.getNameAsString(); 
		this.line = annotation.getBegin().map(position -> position.line).orElse(-1);
	}
	
	public TypeNameWrapper(ClassOrInterfaceDeclaration classOrInterface) {
		this.name = classOrInterface.getNameAsString(); 
		this.line = classOrInterface.getBegin().map(position -> position.line).orElse(-1);
	}
	
	public TypeNameWrapper(EnumDeclaration enumDeclaration) {
		this.name = enumDeclaration.getNameAsString(); 
		this.line = enumDeclaration.getBegin().map(position -> position.line).orElse(-1);
	}

	public String getName() {
		return name;
	}

	public int getLine() {
		return line;
	}
	
	

}
