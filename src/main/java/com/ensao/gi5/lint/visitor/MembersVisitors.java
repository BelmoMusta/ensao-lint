package com.ensao.gi5.lint.visitor;


import com.ensao.gi5.lint.wrapper.NominationWrapper;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;
import java.util.Set;

public class MembersVisitors  extends VoidVisitorAdapter<Set<String>> {
    @Override
    public void visit(ClassOrInterfaceDeclaration classe, Set<String> arg) {
        NodeList<BodyDeclaration<?>> members = classe.getMembers();

        for (int i = 0; i < members.size(); i++) {
            if (members.get(i) instanceof FieldDeclaration) {
                arg.add(((FieldDeclaration) members.get(i)).getVariables().toString());
                //System.out.println(members.get(i).toString());
            }
        }
        super.visit(classe, arg);
    }





}




