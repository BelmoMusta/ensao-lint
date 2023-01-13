package com.ensao.gi5.lint.visitor;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class UnusedPrivateMethodVisitor extends VoidVisitorAdapter<Void> {

    private MethodDeclaration method;
    private boolean isUsed = false;

//    public UnusedPrivateMethodVisitor(MethodDeclaration method){
//        this.method = method;
//    }

    public void setMethod(MethodDeclaration method){
        this.method = method;
    }

    @Override
    public void visit(MethodCallExpr methodCallExpr, Void arg){
        if(methodCallExpr.getName().asString().equals(method.getName().asString())){
            isUsed = true;
        }
        super.visit(methodCallExpr, arg);
    }

    public boolean isUsed(){
        return isUsed;
    }
}
