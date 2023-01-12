package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;

public class NamingWrapper {

    private final int line;
    private final String name;

    private final String type;

    public int getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public NamingWrapper(ClassOrInterfaceDeclaration classOrInterfaceDeclaration) {
        this.name = classOrInterfaceDeclaration.getNameAsString();
        this.type = classOrInterfaceDeclaration.isInterface()?"L'Interface ":"La Class ";
        this.line = classOrInterfaceDeclaration.getBegin().map(var -> var.line).orElse(-1);
    }

    public NamingWrapper(EnumDeclaration enumDeclaration) {
        this.name = enumDeclaration.getNameAsString();
        this.type = "L'Enumeration ";
        this.line = enumDeclaration.getBegin().map(var -> var.line).orElse(-1);
    }

    public NamingWrapper(AnnotationDeclaration annotationDeclaration) {
        this.name = annotationDeclaration.getNameAsString();
        this.type = "Annotation "+annotationDeclaration.getNameAsString();
        this.line = annotationDeclaration.getBegin().map(var -> var.line).orElse(-1);
    }

}
