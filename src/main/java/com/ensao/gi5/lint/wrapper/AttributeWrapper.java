package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.FieldDeclaration;

public class AttributeWrapper extends ElementWrapper{

	public AttributeWrapper(FieldDeclaration fd) {
		this.name=fd.getVariable(0).getNameAsString();
		this.line=fd.getBegin().map((p -> p.line)).orElse(-1);
	}

}
