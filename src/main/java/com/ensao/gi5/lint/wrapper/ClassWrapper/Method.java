package com.ensao.gi5.lint.wrapper.ClassWrapper;

import java.util.HashSet;
import java.util.Set;

public class Method{
    final private String name;
    final private String returnType;
    final private Set<Parameter> parameters;
    final private String accessSpecifier;
    final private int line;
    private int returnCount;
    private int throwCount;
    private int linesCounts;
    private boolean isUsed;

    public Method(String name, String returnType, String accessSpecifier, int line) {
        this.name = name;
        this.returnType = returnType;
        this.accessSpecifier = accessSpecifier;
        this.parameters = new HashSet<>();
        this.line = line;
        this.linesCounts = 0;
    }

    public String getName() {
        return name;
    }

    public String getAccessSpecifier() {
        return accessSpecifier;
    }

    public String getReturnType() {
        return returnType;
    }

    public Set<Parameter> getParameters() {
        return parameters;
    }

    public void setLinesCount(int lines) {
        this.linesCounts = lines;
    }

    public int getLinesCount() {
        return linesCounts;
    }

    public int getLine() {
        return line;
    }

    public int getReturnCount() {
        return returnCount;
    }

    public void setReturnCount(int returnCount) {
        this.returnCount = returnCount;
    }

    public int getThrowCount() {
        return throwCount;
    }

    public void setThrowCount(int throwCount) {
        this.throwCount = throwCount;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    @Override
    public String toString() {
        return String.format("%s %s(%s)", this.returnType, this.name,
                String.join(", ", parameters.stream().map(Parameter::getType).toArray(String[]::new)));
    }
}
