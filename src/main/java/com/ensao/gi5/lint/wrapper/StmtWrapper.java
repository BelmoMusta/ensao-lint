package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.stmt.Statement;

public class StmtWrapper {
    final Statement statement;
    final int ligne;


    public StmtWrapper(Statement statement) {
        this.statement = statement;
        this.ligne = statement.getBegin().map(c->c.line).orElse(-1);
    }

    public Statement getStatement() {
        return statement;
    }

    public int getLigne() {
        return ligne;
    }
}
