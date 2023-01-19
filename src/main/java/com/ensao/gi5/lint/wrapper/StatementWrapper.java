package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.stmt.Statement;

public class StatementWrapper {
    final String ruleId;
    final Statement statement;
    final int line;


    public StatementWrapper(String ruleId, Statement statement) {
        this.ruleId = ruleId;
        this.statement = statement;
        this.line = statement.getBegin().map(p->p.line).orElse(-1);
    }

    public String getRuleId() {
        return ruleId;
    }

    public Statement getStatement() {
        return statement;
    }

    public int getLine() {
        return line;
    }
}
