package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.ConstantVariablesOfClassesWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class ConstantVariablesOfClassesVisitors extends VoidVisitorAdapter<Set<ConstantVariablesOfClassesWrapper>> {

    @Override
    public void visit(FieldDeclaration fieldDeclaration, Set<ConstantVariablesOfClassesWrapper> arg){

        if(fieldDeclaration.isFinal())
            arg.add(new ConstantVariablesOfClassesWrapper(fieldDeclaration));
        super.visit(fieldDeclaration, arg);
    }
}
