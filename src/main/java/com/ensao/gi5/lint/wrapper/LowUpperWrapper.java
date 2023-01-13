package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.expr.SimpleName;

import java.util.Objects;

public class LowUpperWrapper {
    private final String fieldName;
    private final int line;
    public LowUpperWrapper(SimpleName simpleName) {
        this.fieldName = simpleName.toString();
        this.line = simpleName.getBegin().map(p ->p.line).orElse(-1);
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
