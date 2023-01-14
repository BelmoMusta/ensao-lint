package com.ensao.gi5.lint.visitor;
import com.ensao.gi5.lint.wrapper.ruleQuatreWrapper;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;
public class ruleQuatreVisitor  extends VoidVisitorAdapter<Set<ruleQuatreWrapper>> {
    @Override
    public void visit(com.github.javaparser.ast.body.FieldDeclaration n, Set<ruleQuatreWrapper> arg) {
        super.visit(n, arg);
        arg.add(new ruleQuatreWrapper(n));
    }
}
