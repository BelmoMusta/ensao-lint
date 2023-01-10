package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;

public class TypeNameWrapper extends ElementWrapper{

	public TypeNameWrapper(ClassOrInterfaceDeclaration classOrInterfaceDeclaration) {
		this.name = classOrInterfaceDeclaration.getNameAsString();
		this.line = classOrInterfaceDeclaration.getBegin().map(p -> p.line).orElse(-1);
	}

	public TypeNameWrapper(EnumDeclaration enumDeclaration) {
		this.name = enumDeclaration.getNameAsString();
		this.line = enumDeclaration.getBegin().map(p -> p.line).orElse(-1);
	}

	public TypeNameWrapper(AnnotationDeclaration annotationDeclaration) {
		this.name = annotationDeclaration.getNameAsString();
		this.line = annotationDeclaration.getBegin().map(p -> p.line).orElse(-1);
	}

}
