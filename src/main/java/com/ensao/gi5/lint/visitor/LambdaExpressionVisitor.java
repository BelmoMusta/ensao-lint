package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.LocalVariableNameWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;
import java.util.Set;

public class LambdaExpressionVisitor extends VoidVisitorAdapter<Set<LambdaExpr>> {

    @Override
    public void visit(LambdaExpr lambdaExpr, Set<LambdaExpr> arg){
        List<LambdaExpr> lambdaExprList = lambdaExpr.findAll(LambdaExpr.class);
        for (LambdaExpr lambdaExpr1 : lambdaExprList) {
            if(lambdaExpr1.getBody().isBlockStmt() || lambdaExpr1.getBody().isExpressionStmt())
                arg.add(lambdaExpr1);
        }
        super.visit(lambdaExpr, arg);
    }
}
