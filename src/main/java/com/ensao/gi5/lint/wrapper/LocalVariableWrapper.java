package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Utils;
import com.github.javaparser.ast.body.VariableDeclarator;

public class LocalVariableWrapper {

    private String localVariableFQN;
    private String localVariable;
    private int line;

    public LocalVariableWrapper(VariableDeclarator variableDeclarator){
        this.localVariableFQN = variableDeclarator.getNameAsString();
        this.localVariable = Utils.convertFQNToSimpleClassName(variableDeclarator.getNameAsString());
        this.line = variableDeclarator.getBegin().map(p ->p.line).orElse(-1);
    }

    @Override
    public String toString() {
        return localVariableFQN;
    }

    public int getLine() {
        return line;
    }
}
