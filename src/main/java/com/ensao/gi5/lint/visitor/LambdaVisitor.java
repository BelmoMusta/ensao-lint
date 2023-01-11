package com.ensao.gi5.lint.visitor;

import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Hashtable;

public class LambdaVisitor extends VoidVisitorAdapter<Void> {
    private Hashtable<String,Integer> intuitiveLambdas;
    public LambdaVisitor(Hashtable<String,Integer> intuitiveLambdas){
        this.intuitiveLambdas = intuitiveLambdas;
    }

    @Override
    public void visit(LambdaExpr n, Void arg) {
        super.visit(n, arg);
        if(isIntuitiveLambda(n)){
            intuitiveLambdas.put(n.toString(),n.getBegin().get().line);
        }
    }

    private boolean isIntuitiveLambda(LambdaExpr lambda) {
        // there are multiple case
        // for example checking if the lambda expression has only one statement and it's a method call
        return lambda.getBody().isExpressionStmt() && lambda.getBody().asExpressionStmt().getExpression().isMethodCallExpr();
    }
}