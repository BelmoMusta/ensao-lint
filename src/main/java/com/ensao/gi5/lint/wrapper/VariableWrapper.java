package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.expr.SimpleName;

public class VariableWrapper {
    private String name;
    private int lineNum;

    public VariableWrapper(SimpleName varName) {
        this.name = varName.toString();
    }

    public VariableWrapper(SimpleName varName, int line) {
        this.name = varName.getIdentifier();
        this.lineNum = line;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLineNum() {
        return this.lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }
    public boolean startsWithLowerCase(String name){
        return Character.isLowerCase(name.charAt(0));
    }
}