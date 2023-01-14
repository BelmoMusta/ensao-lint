package com.ensao.gi5.lint.visitor;
import com.ensao.gi5.lint.wrapper.LocalVariableWrapper;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import java.util.Set;

public class LocalVariableVisitor extends VoidVisitorAdapter<Set<LocalVariableWrapper>> {
    @Override
    public void visit(VariableDeclarationExpr v, Set<LocalVariableWrapper> arg){
        v.getVariables().forEach(variableDeclarator -> arg.add(new LocalVariableWrapper(variableDeclarator.getName())));
        super.visit(v, arg);
    }
}