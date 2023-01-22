package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.wrapper.SimpleWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class NameVisitors extends VoidVisitorAdapter<List<SimpleWrapper>> {

    @Override
    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, List<SimpleWrapper> arg) {

        arg.add(new SimpleWrapper(classOrInterfaceDeclaration.getName(), Constantes.LINT_REG_002));
        super.visit(classOrInterfaceDeclaration, arg);
    }

    @Override
    public void visit(VariableDeclarationExpr variableDeclarationExpr, List<SimpleWrapper> arg) {

        variableDeclarationExpr.getVariables().forEach(variable -> arg.add(new SimpleWrapper(variable.getName(), Constantes.LINT_REG_003)));

        super.visit(variableDeclarationExpr, arg);
    }



}
