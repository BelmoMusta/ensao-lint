package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Utils;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;

public class EnumElementWrapper extends Wrapper{

    private final String className;


    public EnumElementWrapper(EnumConstantDeclaration enumConstantDeclaration) {
        super(enumConstantDeclaration.getNameAsString(),enumConstantDeclaration.getBegin().map(var -> var.line).orElse(-1));
        this.className = Utils.findClassName(enumConstantDeclaration);

    }

    public String getClassName() {
        return className;
    }

}
