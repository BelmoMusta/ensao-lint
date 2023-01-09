package com.ensao.gi5.lint.wrapper;

public class VariableWrapper {
    final private String name;
    final private String method;
    final private String className;
    final private int usageCount;
    final private int line;

    public VariableWrapper(String name, String method, String className, int count, int line) {
        this.name = name;
        this.method = method;
        this.className = className;
        this.usageCount = count;
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public String getMethod() {
        return method;
    }

    public String getClassName() {
        return className;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public int getLine() {
        return line;
    }
}
