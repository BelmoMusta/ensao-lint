package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.NominationWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class NominationVisitors extends VoidVisitorAdapter<Set<NominationWrapper>> {
    @Override
    public void visit(ClassOrInterfaceDeclaration classOrInterface, Set<NominationWrapper> arg) {
        arg.add(new NominationWrapper(classOrInterface.getName()));

        super.visit(classOrInterface, arg);
    }

    @Override
    public void visit(MarkerAnnotationExpr n, Set<NominationWrapper> arg) {
        arg.add(new NominationWrapper(n.getName()));

        super.visit(n, arg);
    }
    @Override
    public void visit(EnumDeclaration n, Set<NominationWrapper> arg) {
        arg.add(new NominationWrapper(n.getName()));

        super.visit(n, arg);
    }



}
