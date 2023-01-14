package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Utils;
import com.github.javaparser.ast.body.FieldDeclaration;

public class ConstantVariablesOfClassesWrapper extends VariableWrapper{
    private final String className;

    public String getClassName() {
        return className;
    }

    public ConstantVariablesOfClassesWrapper(FieldDeclaration fieldDeclaration) {
        super(fieldDeclaration.getVariable(0).getNameAsString(),fieldDeclaration.getBegin().map(character->character.line).orElse(-1));
        this.className = Utils.getClassName(fieldDeclaration);

    }

}
