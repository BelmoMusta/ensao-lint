package com.ensao.gi5.lint.visitor;
import com.ensao.gi5.lint.wrapper.MethodsWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.Set;
public class MethodsVisitor extends VoidVisitorAdapter<Set<MethodsWrapper>> {
    @Override
    public void visit(MethodDeclaration methodDeclaration, Set<MethodsWrapper> arg) {
        arg.add(new MethodsWrapper(methodDeclaration));
    }
}
