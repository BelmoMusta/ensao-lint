package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.ensao.gi5.lint.wrapper.ElementWrapper;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class AnonymousInstantiationVisitor extends VoidVisitorAdapter<List<ElementWrapper>> {
	@Override
	public void visit(ObjectCreationExpr o, List<ElementWrapper> arg) {
		if (o.getAnonymousClassBody().isPresent()) {
			String name = o.getTypeAsString();
			int line = o.getBegin().map(p -> p.line).orElse(-1);
			arg.add(new ElementWrapper(name, line));
		}
		super.visit(o, arg);
	}
}
