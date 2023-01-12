package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.ReturnCountWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.stmt.ReturnStmt;
import java.util.List;

public class ReturnCountVisitor extends VoidVisitorAdapter<List<ReturnCountWrapper>> {

    @Override
    public void visit(MethodDeclaration n, List<ReturnCountWrapper> arg) {
        super.visit(n, arg);
        int returnCount = n.findAll(ReturnStmt.class).size();
        int line = n.getRange().isPresent()?n.getRange().get().begin.line:0;
        arg.add(new ReturnCountWrapper(n.getNameAsString(), returnCount ,line));
    }
}