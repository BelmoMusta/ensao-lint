package com.ensao.gi5.lint.wrapper;

public abstract class AbstractWrapper {
    private final int  line;

    protected AbstractWrapper(int line) {
        this.line = line;
    }

    public int getLine() {
        return this.line;
    }
}
