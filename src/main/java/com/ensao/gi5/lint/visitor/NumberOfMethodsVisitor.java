package com.ensao.gi5.lint.visitor;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

//we don't need a wrapper since we will use only the MethodDeclaration
public class NumberOfMethodsVisitor extends VoidVisitorAdapter<Set<MethodDeclaration>> {

    @Override
    public void visit(MethodDeclaration methodDeclaration, Set<MethodDeclaration> arg){
        arg.add( methodDeclaration);
        super.visit(methodDeclaration,arg);
    }

}
