package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.stmt.Statement;

public class StatementWrapper {
	private final String statement;
	private final int line;
	
	public StatementWrapper(Statement statement) {
		this.statement = statement.toString();
		this.line = statement.getBegin().map(p ->p.line).orElse(-1);
	}
	public StatementWrapper(String statement, int line) {
		this.statement = statement;
		this.line = line;
	}
	

	public String getStatement() {
		return statement;
	}

	public int getLine() {
		return line;
	}
	
	

}
