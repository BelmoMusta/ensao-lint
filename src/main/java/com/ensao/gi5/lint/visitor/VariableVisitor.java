package com.ensao.gi5.lint.visitor;
import com.ensao.gi5.lint.wrapper.VariableWrapper;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.Set;

public class VariableVisitor extends VoidVisitorAdapter<Set<VariableWrapper>> {
    @Override
    public void visit(VariableDeclarator variableDeclarator, Set<VariableWrapper> arg) {
        arg.add(new VariableWrapper(variableDeclarator));
    }
}
