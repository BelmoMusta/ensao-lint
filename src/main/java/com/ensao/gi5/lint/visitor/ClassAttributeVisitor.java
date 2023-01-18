package com.ensao.gi5.lint.visitor;
import com.ensao.gi5.lint.wrapper.ClassAttributeWrapper;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import java.util.Set;

public class ClassAttributeVisitor extends VoidVisitorAdapter<Set<ClassAttributeWrapper>> {
    @Override
    public void visit(com.github.javaparser.ast.body.FieldDeclaration n, Set<ClassAttributeWrapper> arg) {
        super.visit(n, arg);
        arg.add(new ClassAttributeWrapper(n));
    }
}