package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.ensao.gi5.lint.wrapper.NameWrapper;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LocalVariableNameVisitor extends VoidVisitorAdapter<List<NameWrapper>> {

	@Override
	public void visit(VariableDeclarator n, List<NameWrapper> arg) {
		String name = n.getNameAsString();
		int line = n.getBegin().map(pos -> pos.line).orElse(-1);
		arg.add(new NameWrapper(name, line)); 
		super.visit(n, arg);
	}


}
