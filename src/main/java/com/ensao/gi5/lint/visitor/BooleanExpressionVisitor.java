package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.BooleanExpressionWrapper;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class BooleanExpressionVisitor extends VoidVisitorAdapter<List<BooleanExpressionWrapper>> {

    @Override
    public void visit(ConditionalExpr n, List<BooleanExpressionWrapper> arg) {
        arg.add(new BooleanExpressionWrapper(n, "Conditional Expression"));
        super.visit(n, arg);
    }

    @Override
    public void visit(DoStmt n, List<BooleanExpressionWrapper> arg) {
        arg.add(new BooleanExpressionWrapper(n, "Do While Statement"));
        super.visit(n, arg);
    }

    @Override
    public void visit(IfStmt n, List<BooleanExpressionWrapper> arg) {
        arg.add(new BooleanExpressionWrapper(n, "If Statement"));
        super.visit(n, arg);
    }

    @Override
    public void visit(WhileStmt n, List<BooleanExpressionWrapper> arg) {
        arg.add(new BooleanExpressionWrapper(n, "While Statement"));
        super.visit(n, arg);
    }

}
