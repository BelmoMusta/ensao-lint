package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Utils;
import com.github.javaparser.ast.body.TypeDeclaration;

public class TypeNameWrapper<T extends TypeDeclaration<?>> {

	private final NameWrapper nameWrapper; 

	public TypeNameWrapper(T type) {
		String name = type.getNameAsString();
		int line = Utils.getLine(type.getBegin());
		this.nameWrapper = new NameWrapper(name, line);
	}

	public NameWrapper getNameWrapper() {
		return nameWrapper;
	}

}
