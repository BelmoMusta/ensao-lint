package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RoleTroisWrapper;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class RoleTroisVisitor extends VoidVisitorAdapter<Set<RoleTroisWrapper>> {
    @Override
    public void visit(VariableDeclarationExpr v, Set<RoleTroisWrapper> arg){
        v.getVariables().forEach(variableDeclarator -> arg.add(new RoleTroisWrapper(variableDeclarator.getName())));
        super.visit(v, arg);
    }
}
