package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.AttributeWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class AttributeVisitor extends VoidVisitorAdapter<List<AttributeWrapper>> {

    @Override
    public void visit(FieldDeclaration n, List<AttributeWrapper> arg) {
        arg.add(new AttributeWrapper(n));
        super.visit(n, arg);
    }
}
