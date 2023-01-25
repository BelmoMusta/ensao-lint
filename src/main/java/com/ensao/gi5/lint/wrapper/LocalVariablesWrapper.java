package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.VariableDeclarator;

public class LocalVariablesWrapper extends VariableWrapper  {
    
    public LocalVariablesWrapper(VariableDeclarator variableDeclarator) {
        super(variableDeclarator.getName().toString(),variableDeclarator.getBegin().map(character->character.line).orElse(-1));
    }
}
