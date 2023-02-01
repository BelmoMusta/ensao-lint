package com.ensao.gi5.lint.visitor;

import java.util.List;

import com.ensao.gi5.lint.wrapper.ElementWrapper;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class IfElseStmtVisitor extends VoidVisitorAdapter<List<ElementWrapper>> {

	@Override
	public void visit(IfStmt i, List<ElementWrapper> arg) {
		if (!(i.getThenStmt() instanceof BlockStmt)) {
			arg.add(new ElementWrapper(null, i.getBegin().map(p -> p.line).orElse(-1)));
		}
		i.getElseStmt().ifPresent(s -> {
			if (!(s instanceof BlockStmt)) {
				arg.add(new ElementWrapper(null, i.getBegin().map(p -> p.line).orElse(-1)));
			}
		});
	}

}
