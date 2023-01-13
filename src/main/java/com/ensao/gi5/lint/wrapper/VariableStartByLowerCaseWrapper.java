package com.ensao.gi5.lint.wrapper;





import com.github.javaparser.ast.expr.SimpleName;

public class VariableStartByLowerCaseWrapper {	
	private final String fieldName;
	private final int line;
	
	
	
	public VariableStartByLowerCaseWrapper(SimpleName fieldName) {
		
		 this.fieldName = fieldName.asString();
		 this.line = fieldName.getBegin().map(p ->p.line).orElse(-1);

		   
	}
		
	
	public String getFieldName() {
		return this.fieldName;
	}
	public int getLine() {
		return this.line;
	}

}
