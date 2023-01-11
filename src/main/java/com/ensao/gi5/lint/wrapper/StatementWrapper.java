package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.stmt.Statement;

public class StatementWrapper {

	private Statement statement;
	private int line;
	
	public StatementWrapper(Statement statement) {
		this.statement=statement;
		this.line=statement.getBegin().map(p->p.line).orElse(-1);
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}
	
}
