package com.ensao.gi5.lint.wrapper;

import java.util.Objects;

import com.github.javaparser.ast.expr.SimpleName;

public class VariableWrapper {
	private final String fieldName;
	private final int line;
	
	public VariableWrapper(SimpleName simpleName) {
		this.fieldName = simpleName.toString();
		this.line = simpleName.getBegin().map(p ->p.line).orElse(-1);		
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
            return true;
        }

		if (!(o instanceof VariableStartByLowerCaseWrapper)) {
            return false;
        }
		VariableStartByLowerCaseWrapper that = (VariableStartByLowerCaseWrapper) o;
		return Objects.equals(fieldName, that.getFieldName());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(fieldName);
	}	
	
	public String getFieldName() {
		return this.fieldName;
	}
	public int getLine() {
		return this.line;
	}
	

}
