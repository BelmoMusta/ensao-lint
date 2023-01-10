package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.VariableDeclarator;

public class LocalVariableWrapper extends ElementWrapper{
	
	public LocalVariableWrapper(VariableDeclarator variableDeclarator) {
		this.name=variableDeclarator.getNameAsString();
		this.line=variableDeclarator.getBegin().map(p->p.line).orElse(-1);
	}
	
}
