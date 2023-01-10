package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.stmt.Statement;

public class IfElseWrapper {
    private int line;
    private Statement statement;

    public IfElseWrapper(Statement statement){
        this.statement= statement;
        this.line = statement.getBegin().map(character->character.line).orElse(-1);
    }

    public int getLine(){
        return  line;
    }

    public Statement getStatement(){
        return statement;
    }
}
