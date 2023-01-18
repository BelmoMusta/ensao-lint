package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.TypeWrapper;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class TypeVisitor extends VoidVisitorAdapter<List<TypeWrapper>> {

    @Override
    public void visit(AnnotationDeclaration n, List<TypeWrapper> arg) {
        arg.add(new TypeWrapper(n.asTypeDeclaration()));
        super.visit(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, List<TypeWrapper> arg) {
        arg.add(new TypeWrapper(n.asTypeDeclaration()));
        super.visit(n, arg);
    }

    @Override
    public void visit(EnumConstantDeclaration n, List<TypeWrapper> arg) {
        arg.add(new TypeWrapper(n.asTypeDeclaration()));
        super.visit(n, arg);
    }

    @Override
    public void visit(EnumDeclaration n, List<TypeWrapper> arg) {
        arg.add(new TypeWrapper(n.asTypeDeclaration()));
        super.visit(n, arg);
    }
}
