package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.NamingWrapper;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class NamingVisitor extends VoidVisitorAdapter<Set<NamingWrapper>> {

    @Override
    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, Set<NamingWrapper> arg) {
        arg.add(new NamingWrapper(classOrInterfaceDeclaration));
        super.visit(classOrInterfaceDeclaration, arg);
    }



    @Override
    public void visit(AnnotationDeclaration annotationDeclaration,Set<NamingWrapper> arg){
        arg.add(new NamingWrapper(annotationDeclaration));
        super.visit(annotationDeclaration,arg);
    }


    @Override
    public void visit(EnumDeclaration enumDeclaration,Set<NamingWrapper> arg){
        arg.add(new NamingWrapper(enumDeclaration));
        super.visit(enumDeclaration,arg);
    }
}
