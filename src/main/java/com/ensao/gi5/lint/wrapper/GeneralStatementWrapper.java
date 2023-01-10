package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.stmt.Statement;

public class GeneralStatementWrapper {

    private int line;

    private Statement statement;



    public GeneralStatementWrapper(Statement statement){
        this.statement=statement;
        this.line = statement.getBegin().map(character->character.line).orElse(-1);
    }

    public Statement getStatement() {
        return statement;
    }
    public int getLine(){
        return  line;
    }
}
