package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RoleDeuxWrapper;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class RoleDeuxVisitor extends VoidVisitorAdapter<Set<RoleDeuxWrapper>> {
    @Override
    public void visit(ClassOrInterfaceDeclaration roleDeux, Set<RoleDeuxWrapper> roleDeuxWrappers) {
        super.visit(roleDeux, roleDeuxWrappers);
        roleDeuxWrappers.add(new RoleDeuxWrapper(roleDeux));
    }
    @Override
    public void visit(EnumDeclaration enumDeclaration, Set<RoleDeuxWrapper> roleDeuxWrappers) {
        super.visit(enumDeclaration, roleDeuxWrappers);
        roleDeuxWrappers.add(new RoleDeuxWrapper(enumDeclaration));
    }
    @Override
    public void visit(AnnotationDeclaration annotationDeclaration, Set<RoleDeuxWrapper> roleDeuxWrappers) {
        super.visit(annotationDeclaration, roleDeuxWrappers);
        roleDeuxWrappers.add(new RoleDeuxWrapper(annotationDeclaration));
    }
}
