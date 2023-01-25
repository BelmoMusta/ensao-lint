package com.ensao.gi5.lint.wrapper;

public class VariableWrapper {
    private final String name;
    private final int line;
  //  private final int count; // this is for the variables that arent used should be removed REG_16

    public VariableWrapper(String name, int line) {
        this.name = name;
        this.line = line;

    }

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }

}
