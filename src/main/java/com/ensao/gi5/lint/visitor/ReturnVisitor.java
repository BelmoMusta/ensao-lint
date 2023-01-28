package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ReturnVisitor extends VoidVisitorAdapter<List<Integer>> {

	@Override
	public void visit(ReturnStmt rs, List<Integer> arg) {
		arg.add(1);
		super.visit(rs, arg);
	}
	@Override
	public void visit(ThrowStmt ts, List<Integer> arg) {
		arg.add(1);
		super.visit(ts, arg);
	}
}
