package com.ensao.gi5.lint.visitor;

import java.util.Set;
import java.util.regex.Pattern;

import com.ensao.gi5.lint.wrapper.StatementWrapper;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class BooleanExpressionsVisitor extends VoidVisitorAdapter<Set<StatementWrapper>>{
	
	@Override
	public void visit(BlockStmt bs,Set<StatementWrapper> arg) {
		bs.getStatements().stream().filter(s -> Pattern.compile("==|!=|<|>|>=|<=")
                .matcher(s.toString()).find()).forEach(s ->arg.add(new StatementWrapper(s)));
	}
	
}
