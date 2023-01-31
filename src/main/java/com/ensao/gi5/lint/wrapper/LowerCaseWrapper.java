package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.SimpleName;

import java.util.Objects;

public class LowerCaseWrapper {
    private final String fieldName;
    private final int line;



    public LowerCaseWrapper(SimpleName fieldName) {

        this.fieldName = fieldName.asString();
        this.line = fieldName.getBegin().map(p ->p.line).orElse(-1);


    }
    public LowerCaseWrapper(FieldDeclaration fieldName) {
        this.fieldName = fieldName.toString();
        this.line = fieldName.getBegin().map(p ->p.line).orElse(-1);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof LowerCaseWrapper)) {
            return false;
        }
        LowerCaseWrapper that = (LowerCaseWrapper) o;
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
