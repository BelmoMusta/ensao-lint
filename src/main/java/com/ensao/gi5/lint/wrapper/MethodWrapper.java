package com.ensao.gi5.lint.wrapper;



public class MethodWrapper {
	private final int lineCount;
	private final int parameterCount;
	private final int returnCount;
	private final int throwCount;
	private final int line;
	
	public MethodWrapper(int lineCount, int parameterCount, int returnCount, int throwCount ,int line) {
		this.lineCount = lineCount;
		this.parameterCount = parameterCount;
		this.returnCount = returnCount;
		this.throwCount = throwCount;
		this.line = line;
	}
	
	
	
	public int getLineCount() {
		return lineCount;
	}

	public int getParameterCount() {
		return parameterCount;
	}

	public int getReturnCount() {
		return returnCount;
	}

	public int getThrowCount() {
		return throwCount;
	}
	
	public int getLine() {
		return this.line;
	}

}
