package com.ensao.gi5.lint.visitor;
import com.ensao.gi5.lint.wrapper.ruleTroisWrapper;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;
public class ruleTroisVisitor extends VoidVisitorAdapter<Set<ruleTroisWrapper>> {
    @Override
    public void visit(VariableDeclarationExpr v, Set<ruleTroisWrapper> arg){
        v.getVariables().forEach(variableDeclarator -> arg.add(new ruleTroisWrapper(variableDeclarator.getName())));
        super.visit(v, arg);
    }
}