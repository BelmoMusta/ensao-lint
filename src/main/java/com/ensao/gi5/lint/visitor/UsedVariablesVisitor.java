package com.ensao.gi5.lint.visitor;

import java.util.Set;

import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class UsedVariablesVisitor extends VoidVisitorAdapter<Set<String>> {
	@Override
	public void visit(NameExpr n, Set<String> arg) {
		arg.add(n.getNameAsString());
		super.visit(n, arg);
	}
}
