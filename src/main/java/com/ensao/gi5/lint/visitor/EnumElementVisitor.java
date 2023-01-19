package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.EnumElementWrapper;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class EnumElementVisitor extends VoidVisitorAdapter<Set<EnumElementWrapper>> {
    public void visit(EnumConstantDeclaration enumConstantDeclaration, Set<EnumElementWrapper> arg) {
        arg.add(new EnumElementWrapper(enumConstantDeclaration));
    }
}
