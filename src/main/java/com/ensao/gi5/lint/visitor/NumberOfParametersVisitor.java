package com.ensao.gi5.lint.visitor;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class NumberOfParametersVisitor extends VoidVisitorAdapter<Set<ConstructorDeclaration>> {

    @Override
    public void visit(ConstructorDeclaration constructorDeclaration, Set<ConstructorDeclaration> arg){
        arg.add( constructorDeclaration);
        super.visit(constructorDeclaration,arg);
    }
}
