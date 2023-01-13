package com.ensao.gi5.lint.wrapper;



import com.github.javaparser.ast.expr.SimpleName;

public class VariableWrapper {
	private final String fieldName;
	private final int line;
	
	public VariableWrapper(SimpleName simpleName) {
		this.fieldName = simpleName.toString();
		this.line = simpleName.getBegin().map(p ->p.line).orElse(-1);		
	}		
	
	public String getFieldName() {
		return this.fieldName;
	}
	public int getLine() {
		return this.line;
	}
	

}
