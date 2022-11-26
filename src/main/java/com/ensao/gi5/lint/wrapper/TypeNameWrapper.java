package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.TypeDeclaration;

public class TypeNameWrapper<T extends TypeDeclaration<?>> {

	private final NameWrapper nameWrapper; 

	public TypeNameWrapper(T type) {
		String name = type.getNameAsString();
		int line = type.getBegin().map(position -> position.line).orElse(-1);
		this.nameWrapper = new NameWrapper(name, line);
	}

	public NameWrapper getNameWrapper() {
		return nameWrapper;
	}

}
