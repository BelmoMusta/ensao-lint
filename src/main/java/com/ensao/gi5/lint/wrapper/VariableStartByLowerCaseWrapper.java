package com.ensao.gi5.lint.wrapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


import com.github.javaparser.ast.stmt.Statement;

public class VariableStartByLowerCaseWrapper {	
	private final Statement fieldName;
	private final int line;
	
	public VariableStartByLowerCaseWrapper(Statement fieldName) {
		
		 this.fieldName = fieldName;
		 this.line = fieldName.getBegin().map(p ->p.line).orElse(-1);
		   
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
	
	@Override
	public String toString() {
		return fieldName.toString();
	}
	public Statement getFieldName() {
		return this.fieldName;
	}
	public int getLine() {
		return this.line;
	}

}
