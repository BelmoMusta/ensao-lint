package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Utils;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;

public class TypeNamingWrapper {

    private String typeNamingFQN;
    private String typeNaming;
    private int line;

    public TypeNamingWrapper(ClassOrInterfaceDeclaration classOrInterface){
        this.typeNamingFQN = classOrInterface.getNameAsString();
        this.typeNaming = Utils.convertFQNToSimpleClassName(classOrInterface.getNameAsString());
        this.line = classOrInterface.getBegin().map(p ->p.line).orElse(-1);
    }

    public TypeNamingWrapper(EnumDeclaration enumDeclaration){
        this.typeNamingFQN = enumDeclaration.getNameAsString();
        this.typeNaming = Utils.convertFQNToSimpleClassName(enumDeclaration.getNameAsString());
        this.line = enumDeclaration.getBegin().map(p ->p.line).orElse(-1);
    }

    public TypeNamingWrapper(AnnotationDeclaration annotationDeclaration){
        this.typeNamingFQN = annotationDeclaration.getNameAsString();
        this.typeNaming = Utils.convertFQNToSimpleClassName(annotationDeclaration.getNameAsString());
        this.line = annotationDeclaration.getBegin().map(p -> p.line).orElse(-1);
    }
    public TypeNamingWrapper(String importDeclaration) {
        this.typeNamingFQN = importDeclaration;
        this.typeNaming = Utils.convertFQNToSimpleClassName(importDeclaration);
        this.line = 0;
    }

    public TypeNamingWrapper(NameExpr name) {
        this(name.getNameAsString());
    }

    public TypeNamingWrapper(Name name) {
        this(name.asString());
    }

    public TypeNamingWrapper(SimpleName name) {
        this(name.asString());
    }

    @Override
    public String toString() {
        return typeNamingFQN;
    }

    public int getLine() {
        return line;
    }
}
