package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.wrapper.NameWrapper;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class AnonymousClassVisitor extends VoidVisitorAdapter<List<NameWrapper>> {

	@Override
	public void visit(ObjectCreationExpr n, List<NameWrapper> arg) {
		
		if(n.getAnonymousClassBody().isPresent()) {
			String type = n.getTypeAsString();
			int line = Utils.getLine(n.getBegin());
			arg.add(new NameWrapper(type, line));
		}

		super.visit(n, arg);
	}

	
}
