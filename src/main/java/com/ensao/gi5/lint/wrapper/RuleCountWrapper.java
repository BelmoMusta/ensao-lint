package com.ensao.gi5.lint.wrapper;

public class RuleCountWrapper {
    private String name;
    private int returnCount;
    private int line;

    public RuleCountWrapper(String name,int line) {
        this.name = name;
        this.line = line;
    }
    public RuleCountWrapper(int returnCount,int line) {
        this.returnCount = returnCount;
        this.line = line;
    }


    public int getCount() {
        return returnCount;
    }
    public  int getLine(){
        return line;
    }
}
