package com.ensao.gi5.lint.wrapper;

public class CatchExceptionWrapper {
    private String methodName;
    private String exceptionName;
    private int line;

    public CatchExceptionWrapper(String methodName, String exceptionName,int line) {
        this.methodName = methodName;
        this.exceptionName = exceptionName;
        this.line= line;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getExceptionName() {
        return exceptionName;
    }
    public int getLine(){
        return line;
    }
}
