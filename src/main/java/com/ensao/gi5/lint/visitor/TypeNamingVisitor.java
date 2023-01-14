package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.TypeNamingWrapper;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class TypeNamingVisitor extends VoidVisitorAdapter<Set<TypeNamingWrapper>> {

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Set<TypeNamingWrapper> arg) {
        arg.add(new TypeNamingWrapper(n));
        super.visit(n, arg);
    }

    @Override
    public void visit(EnumDeclaration n, Set<TypeNamingWrapper> arg) {
        arg.add(new TypeNamingWrapper(n));
        super.visit(n, arg);
    }

    @Override
    public void visit(AnnotationDeclaration n, Set<TypeNamingWrapper> arg) {
        arg.add(new TypeNamingWrapper(n));
        super.visit(n, arg);
    }
}
