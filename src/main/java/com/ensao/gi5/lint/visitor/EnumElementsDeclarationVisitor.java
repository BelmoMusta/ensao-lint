package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.ClassAttributesWrapper;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class EnumElementsDeclarationVisitor extends VoidVisitorAdapter<Set<ClassAttributesWrapper>> {

    @Override
    public void visit(EnumConstantDeclaration n, Set<ClassAttributesWrapper> arg) {
        arg.add(new ClassAttributesWrapper(n));
        super.visit(n, arg);
    }
}
