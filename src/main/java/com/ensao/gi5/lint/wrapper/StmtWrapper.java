package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.stmt.Statement;

public class StmtWrapper {
    final String ruleId;
    final Statement statement;
    final int ligne;


    public StmtWrapper(String ruleId, Statement statement) {
        this.ruleId = ruleId;
        this.statement = statement;
        this.ligne = statement.getBegin().map(p->p.line).orElse(-1);
    }

    public String getRuleId() {
        return ruleId;
    }

    public Statement getStatement() {
        return statement;
    }

    public int getLigne() {
        return ligne;
    }
}
