package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.LocalVariablesWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;
import java.util.Set;

public class LocalVariablesVisitors extends VoidVisitorAdapter<Set<LocalVariablesWrapper>> {

    @Override
    public void visit(MethodDeclaration methodDeclaration, Set<LocalVariablesWrapper> arg){
        List<VariableDeclarator> allLocalVariables = methodDeclaration.getBody().get()
                .findAll(VariableDeclarator.class);
        for (VariableDeclarator variableDeclarator : allLocalVariables) {
            arg.add(new LocalVariablesWrapper(variableDeclarator));
        }
        super.visit(methodDeclaration, arg);
    }
}
