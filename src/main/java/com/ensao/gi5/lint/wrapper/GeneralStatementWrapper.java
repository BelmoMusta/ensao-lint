package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.stmt.Statement;

public class GeneralStatementWrapper extends Wrapper{

    private Statement statement;


    public GeneralStatementWrapper(Statement statement){
        super("",statement.getBegin().map(character->character.line).orElse(-1));
        this.statement=statement;
    }

    public Statement getStatement() {
        return statement;
    }
}
