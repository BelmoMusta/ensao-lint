package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.Level;
import com.ensao.gi5.lint.wrapper.SimpleWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


import java.util.List;

public class NamesVisitor extends VoidVisitorAdapter<List<SimpleWrapper>> {

    @Override
    public void visit(ClassOrInterfaceDeclaration n, List<SimpleWrapper> arg) {
        arg.add(new SimpleWrapper(n.getName(), Constantes.LINT_REG_002));
        super.visit(n, arg);
    }

    @Override
    public void visit(VariableDeclarationExpr n, List<SimpleWrapper> arg) {
        n.getVariables().forEach(e -> arg.add(
                new SimpleWrapper(e.getName(), Constantes.LINT_REG_003)
        ));
        super.visit(n, arg);
    }

    @Override
    public void visit(FieldDeclaration n, List<SimpleWrapper> arg) {
        if(!n.isStatic()) {
            n.getVariables().forEach(e -> arg.add(
                    new SimpleWrapper(e.getName(), Constantes.LINT_REG_004)
            ));
        } else{
            n.getVariables().forEach(e -> arg.add(
                    new SimpleWrapper(e.getName(), Constantes.LINT_REG_005)
            ));
        }

        n.getVariables().forEach(e -> arg.add(
                new SimpleWrapper(e.getName(), Constantes.LINT_REG_013)
                        .addAccessSpecifier(n.getAccessSpecifier().asString())
        ));
        super.visit(n, arg);
    }



}
