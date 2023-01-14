package com.ensao.gi5.lint.visitor;
import com.ensao.gi5.lint.wrapper.IfElseWrapper;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.Set;

public class IfElseVisitor extends VoidVisitorAdapter<Set<IfElseWrapper>> {
    @Override
    public void visit(IfStmt ifStmt, Set<IfElseWrapper> arg) {
        arg.add(new IfElseWrapper(ifStmt));
        if (ifStmt.getElseStmt().isPresent()) {
            arg.add(new IfElseWrapper(ifStmt, "else"));
        }
    }
}


