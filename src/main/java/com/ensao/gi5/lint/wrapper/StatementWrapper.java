package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.stmt.Statement;

public class StatementWrapper {

    //Les attributs
    final String ruleId;
    final Statement statement;
    final int line;

    //Le constructeur générique
    public StatementWrapper(String ruleId, Statement statement) {
        this.ruleId = ruleId;
        this.statement = statement;
        this.line = statement.getBegin().map(p->p.line).orElse(-1);
    }

    //Les méthodes
    public String getRuleId() { return this.ruleId; }
    public Statement getStatement() { return this.statement; }
    public int getLine() { return this.line; }

}
