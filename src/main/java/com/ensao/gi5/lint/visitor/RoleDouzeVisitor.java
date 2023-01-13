package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RoleDouzeWrapper;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class RoleDouzeVisitor extends VoidVisitorAdapter<Set<RoleDouzeWrapper>> {
    @Override
    public void visit(MethodDeclaration methodDeclaration, Set<RoleDouzeWrapper> arg ){
       arg.add(new RoleDouzeWrapper(methodDeclaration.getParameters().size(), methodDeclaration.getBegin().get().line));
       super.visit(methodDeclaration, arg);
    }
    @Override
    public void visit(ConstructorDeclaration constructorDeclaration, Set<RoleDouzeWrapper> arg ){
        arg.add(new RoleDouzeWrapper(constructorDeclaration.getParameters().size(), constructorDeclaration.getBegin().get().line));
        super.visit(constructorDeclaration, arg);
    }

}
