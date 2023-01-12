package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RuleWrapper;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;

public class LambdaVisitor extends VoidVisitorAdapter<List<RuleWrapper>> {
    private List<RuleWrapper> intuitiveLambdas;
    public LambdaVisitor(List<RuleWrapper> intuitiveLambdas){
        this.intuitiveLambdas = intuitiveLambdas;
    }

    @Override
    public void visit(LambdaExpr n, List<RuleWrapper> arg) {
        super.visit(n, arg);
        if(isIntuitiveLambda(n)){
            intuitiveLambdas.add(new RuleWrapper(n.toString(),n.getBegin().get().line));
        }
    }

    private boolean isIntuitiveLambda(LambdaExpr lambda) {
        // there are multiple case
        // for example checking if the lambda expression has only one statement and it's a method call
        return lambda.getBody().isExpressionStmt() && lambda.getBody().asExpressionStmt().getExpression().isMethodCallExpr();
    }
}