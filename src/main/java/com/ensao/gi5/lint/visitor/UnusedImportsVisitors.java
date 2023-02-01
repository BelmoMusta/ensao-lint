package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.ImportWrapper;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Optional;
import java.util.Set;

public class UnusedImportsVisitors extends VoidVisitorAdapter<Set<ImportWrapper>> {

    @Override
    public void visit(ClassOrInterfaceType classOrInterfaceType, Set<ImportWrapper> arg) {
        arg.add(new ImportWrapper(classOrInterfaceType.getName()));
        super.visit(classOrInterfaceType, arg);
    }

    @Override
    public void visit(MarkerAnnotationExpr n, Set<ImportWrapper> arg) {
        arg.add(new ImportWrapper(n.getName()));
        super.visit(n, arg);
    }

    @Override
    public void visit(SingleMemberAnnotationExpr n, Set<ImportWrapper> arg) {
        arg.add(new ImportWrapper(n.getName()));
        super.visit(n, arg);
    }

    @Override
    public void visit(NormalAnnotationExpr n, Set<ImportWrapper> arg) {
        arg.add(new ImportWrapper(n.getName()));
        super.visit(n, arg);
    }

    @Override
    public void visit(FieldAccessExpr n, Set<ImportWrapper> arg) {
        Optional.ofNullable(n.getScope())
                .filter(Expression::isNameExpr)
                .map(NameExpr.class::cast)
                .map(ImportWrapper::new)
                .ifPresent(arg::add);
        super.visit(n, arg);
    }

    @Override
    public void visit(MethodCallExpr n, Set<ImportWrapper> arg) {
        n.getScope()
                .filter(Expression::isNameExpr)
                .map(NameExpr.class::cast)
                .map(ImportWrapper::new)
                .ifPresent(arg::add);
        super.visit(n, arg);
    }
}
