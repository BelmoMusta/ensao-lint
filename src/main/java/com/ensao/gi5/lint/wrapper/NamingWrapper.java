package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;

public class NamingWrapper extends Wrapper{



    private final String type;



    public NamingWrapper(ClassOrInterfaceDeclaration classOrInterfaceDeclaration) {
        super(classOrInterfaceDeclaration.getNameAsString(),classOrInterfaceDeclaration.getBegin().map(var -> var.line).orElse(-1));
        this.type = classOrInterfaceDeclaration.isInterface()?"L'Interface ":"La Class ";
    }

    public NamingWrapper(EnumDeclaration enumDeclaration) {
        super(enumDeclaration.getNameAsString(),enumDeclaration.getBegin().map(var -> var.line).orElse(-1));
        this.type = "L'Enumeration ";
    }

    public NamingWrapper(AnnotationDeclaration annotationDeclaration) {
        super(annotationDeclaration.getNameAsString(),annotationDeclaration.getBegin().map(var -> var.line).orElse(-1));
        this.type = "Annotation "+annotationDeclaration.getNameAsString();
    }

    public String getType() {
        return type;
    }


}
