package com.ensao.gi5.lint.visitor;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class MethodBodyVisitor extends VoidVisitorAdapter<Set<MethodDeclaration>> {

    @Override
    public void visit(MethodDeclaration methodDeclaration, Set<MethodDeclaration> arg){

        arg.add(methodDeclaration);
    }
}
