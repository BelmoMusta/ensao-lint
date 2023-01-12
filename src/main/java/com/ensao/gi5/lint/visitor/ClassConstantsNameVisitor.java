package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.ClassAttributsNameWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class ClassConstantsNameVisitor extends VoidVisitorAdapter<Set<ClassAttributsNameWrapper>> {

    @Override
    public void visit(FieldDeclaration fieldDeclaration, Set<ClassAttributsNameWrapper> arg){

        //Les constants ne sont pas prise en compte
        if(fieldDeclaration.isFinal())
            arg.add(new ClassAttributsNameWrapper(fieldDeclaration));
        super.visit(fieldDeclaration, arg);
    }
}
