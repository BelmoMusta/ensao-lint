package com.ensao.gi5.lint.visitor;
import com.ensao.gi5.lint.wrapper.RoleSeptWrapper;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set; 
public class RoleSeptVisitor extends VoidVisitorAdapter<Set<RoleSeptWrapper>> {
    @Override
    public void visit(EnumDeclaration enumDeclaration, Set<RoleSeptWrapper> arg) {
       enumDeclaration.getEntries().forEach(e->arg.add(new RoleSeptWrapper(e.getName())));
       super.visit(enumDeclaration, arg);
    }
}
