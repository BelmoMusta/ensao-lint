package com.ensao.gi5.lint.visitor;

import java.util.Map;

import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.wrapper.NameWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodBodyVisitor extends VoidVisitorAdapter<Map<NameWrapper, Integer>> {

	@Override
	public void visit(MethodDeclaration n, Map<NameWrapper, Integer> arg) {

		n.getBody().ifPresent(blockStmt -> {
			int start = Utils.getLine(blockStmt.getBegin());
			int end = Utils.getLine(blockStmt.getEnd());
			int bodyLength = end - start - 1;
			NameWrapper methodName = new NameWrapper(n.getNameAsString(), start);
			arg.put(methodName, bodyLength);
		});

		super.visit(n, arg);
	}

}
