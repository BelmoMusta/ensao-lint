package com.ensao.gi5.lint.wrapper;

public class MethodParameterWrapper {
    private String methodName;
    private int parameterCount;
    private int line;

    public MethodParameterWrapper(String methodName, int parameterCount ,int line) {
        this.methodName = methodName;
        this.parameterCount = parameterCount;
        this.line = line;
    }
    public String getMethodName() {
        return methodName;
    }

    public int getParameterCount() {
        return parameterCount;
    }
    public int getLine(){
        return line;
    }
}
