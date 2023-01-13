package com.ensao.gi5.lint.visitor;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Optional;
import java.util.Set;

public class AnonymousVisitors extends VoidVisitorAdapter<Set<String>> {
    @Override
    public void visit(ObjectCreationExpr a, Set<String> arg) {
        Optional<NodeList<BodyDeclaration<?>>> members = a.getAnonymousClassBody();

        members.ifPresent(val->arg.add(val.toString()));

        super.visit(a,arg);
    }


}
