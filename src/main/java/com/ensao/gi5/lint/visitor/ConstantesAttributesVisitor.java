package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.ClassAttributesWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class ConstantesAttributesVisitor extends VoidVisitorAdapter<Set<ClassAttributesWrapper>> {

    @Override
    public void visit(FieldDeclaration n, Set<ClassAttributesWrapper> arg) {

        if(n.isFinal()) arg.add(new ClassAttributesWrapper(n));
        super.visit(n, arg);
    }
}
