package com.ensao.gi5.lint.wrapper;

public class ConstructorWrapper {
	private final int constructParamCount;
	private final int line;
	
	public ConstructorWrapper(int constructParamCount, int line) {
		this.constructParamCount = constructParamCount;
		this.line = line;
	}

	public int getConstructParamCount() {
		return constructParamCount;
	}

	public int getLine() {
		return line;
	}
	
}
