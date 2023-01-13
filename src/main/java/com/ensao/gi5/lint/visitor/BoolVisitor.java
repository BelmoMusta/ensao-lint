package com.ensao.gi5.lint.visitor;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.ArrayAccessExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Set;

public class BoolVisitor extends VoidVisitorAdapter<Long> {

    public void visit(Expression e, Long arg) {
        arg=e.stream()
                .filter(node -> node instanceof Expression)
                .map(node -> ( Expression ) node)
                .filter(Expression::isBinaryExpr).count();




        super.visit((ArrayAccessExpr) e, arg);

    }
}
