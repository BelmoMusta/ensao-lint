package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.wrapper.SimpleWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NamesVisitor extends VoidVisitorAdapter<Map<String, List<SimpleWrapper>>> {


    @Override
    public void visit(ClassOrInterfaceDeclaration n, Map<String, List<SimpleWrapper>> arg) {
        initKey(arg, Constantes.LINT_REG_002);
        arg.get(Constantes.LINT_REG_002).add(new SimpleWrapper(n.getName(), Constantes.LINT_REG_002));
        super.visit(n, arg);
    }

    @Override
    public void visit(VariableDeclarationExpr n, Map<String, List<SimpleWrapper>> arg) {
        initKey(arg, Constantes.LINT_REG_003);
        n.getVariables().forEach(e -> arg.get(Constantes.LINT_REG_003).add(
                new SimpleWrapper(e.getName(), Constantes.LINT_REG_003)
        ));
        super.visit(n, arg);
    }

    @Override
    public void visit(FieldDeclaration n, Map<String, List<SimpleWrapper>> arg) {
        initKey(arg, Constantes.LINT_REG_004, Constantes.LINT_REG_005, Constantes.LINT_REG_013);
        if(!n.isStatic()) {
            n.getVariables().forEach(e -> arg.get(Constantes.LINT_REG_004).add(
                    new SimpleWrapper(e.getName(), Constantes.LINT_REG_004)
            ));
        } else{
            n.getVariables().forEach(e -> arg.get(Constantes.LINT_REG_005).add(
                    new SimpleWrapper(e.getName(), Constantes.LINT_REG_005)
            ));
        }

        n.getVariables().forEach(e -> arg.get(Constantes.LINT_REG_013).add(
                new SimpleWrapper(e.getName(), Constantes.LINT_REG_013)
                        .addAccessSpecifier(n.getAccessSpecifier().asString())
        ));
        super.visit(n, arg);
    }



    private void initKey(Map<String , List<SimpleWrapper>> map, String... rulesId){
        for(String ruleId : rulesId)
            map.putIfAbsent(ruleId, new ArrayList<>());
    }



}
