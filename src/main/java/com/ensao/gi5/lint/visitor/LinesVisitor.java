package com.ensao.gi5.lint.visitor;
import com.ensao.gi5.lint.wrapper.LinesWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.Set;

public class LinesVisitor extends VoidVisitorAdapter<Set<LinesWrapper>> {
    @Override
    public void visit(MethodDeclaration methodDeclaration, Set<LinesWrapper> arg) {
        arg.add(new LinesWrapper(methodDeclaration));
    }
}
