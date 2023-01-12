package com.ensao.gi5.lint.wrapper;

public class ReturnCountWrapper {
    private String methodName;
    private int returnCount;
    private int line;

    public ReturnCountWrapper(String methodName, int returnCount ,int line) {
        this.methodName = methodName;
        this.returnCount = returnCount;
        this.line = line;
    }

    public String getMethodName() {
        return methodName;
    }

    public int getReturnCount() {
        return returnCount;
    }
    public  int getLine(){
        return line;
    }
}
