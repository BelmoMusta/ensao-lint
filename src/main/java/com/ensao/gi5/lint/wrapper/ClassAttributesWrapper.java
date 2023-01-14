package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Utils;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;

public class ClassAttributesWrapper {

    private String classAttribureFQN;
    private String classAttribure;
    private int line;

    public ClassAttributesWrapper(FieldDeclaration fieldDeclaration){
        this.classAttribureFQN = fieldDeclaration.getVariable(0).getNameAsString();
        this.classAttribure = Utils.convertFQNToSimpleClassName(fieldDeclaration.getVariable(0).getNameAsString());
        this.line = fieldDeclaration.getBegin().map(p->p.line).orElse(-1);
    }

    public ClassAttributesWrapper(EnumConstantDeclaration enumConstantDeclaration){
        this.classAttribureFQN = enumConstantDeclaration.getNameAsString();
        this.classAttribure = Utils.convertFQNToSimpleClassName(enumConstantDeclaration.getNameAsString());
        this.line = enumConstantDeclaration.getBegin().map(p->p.line).orElse(-1);
    }

    @Override
    public String toString() {
        return classAttribureFQN;
    }

    public int getLine() {
        return line;
    }

}
