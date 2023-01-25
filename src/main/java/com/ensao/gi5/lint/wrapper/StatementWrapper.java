package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.stmt.Statement;

public class StatementWrapper {
    private int line;
    private Statement statement;

    public StatementWrapper(Statement statement) {
        this.line = statement.getBegin().map(character->character.line).orElse(-1);
        this.statement = statement;
    }

    public int getLine() {
        return line;
    }

    public Statement getStatement() {
        return statement;
    }
}
