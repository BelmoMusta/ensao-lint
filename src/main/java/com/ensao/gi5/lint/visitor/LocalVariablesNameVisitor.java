package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.LocalVariableNameWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;
import java.util.Set;

public class LocalVariablesNameVisitor extends VoidVisitorAdapter<Set<LocalVariableNameWrapper>> {

    @Override
    public void visit(MethodDeclaration methodDeclaration, Set<LocalVariableNameWrapper> arg){
        List<VariableDeclarator> allLocalVariables = methodDeclaration.getBody().get()
                                                            .findAll(VariableDeclarator.class);
        for (VariableDeclarator variableDeclarator : allLocalVariables) {
            arg.add(new LocalVariableNameWrapper(variableDeclarator));
        }
        super.visit(methodDeclaration, arg);
    }

}
