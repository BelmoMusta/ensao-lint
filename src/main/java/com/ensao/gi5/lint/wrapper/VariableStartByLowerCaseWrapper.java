package com.ensao.gi5.lint.wrapper;

import java.util.Objects;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.SimpleName;

public class VariableStartByLowerCaseWrapper {
	private final String fieldName;
	
	public VariableStartByLowerCaseWrapper(String fieldName) {
		this.fieldName = fieldName;
	}
	public VariableStartByLowerCaseWrapper(FieldDeclaration fieldName) {
		this.fieldName = fieldName.toString();
	}
	public VariableStartByLowerCaseWrapper(SimpleName fieldName) {
		this.fieldName = fieldName.toString();
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
		return fieldName;
	}
	public String getFieldName() {
		return this.fieldName;
	}

}
