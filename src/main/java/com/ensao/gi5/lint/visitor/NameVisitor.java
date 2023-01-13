package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.wrapper.NameWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NameVisitor extends VoidVisitorAdapter<Map<String, List<NameWrapper>>> {
    @Override
    public void visit(ClassOrInterfaceDeclaration n, Map<String, List<NameWrapper>> arg) {
        initializeKeyOfMap(arg, Constantes.LINT_REG_002);
        arg.get(Constantes.LINT_REG_002).add(new NameWrapper(n.getName(), Constantes.LINT_REG_002));
        super.visit(n, arg);
    }

    @Override
    public void visit(VariableDeclarationExpr vr ,  Map<String, List<NameWrapper>> arg){
        initializeKeyOfMap(arg,Constantes.LINT_REG_003);
        vr.getVariables().forEach(e->arg.get(Constantes.LINT_REG_003).add(new NameWrapper(e.getName(),Constantes.LINT_REG_003)));
        super.visit(vr,arg);
    }

    @Override
    public void visit(FieldDeclaration fl , Map<String, List<NameWrapper>> arg){
        initializeKeyOfMap(arg,Constantes.LINT_REG_004,Constantes.LINT_REG_005,Constantes.LINT_REG_013);
        if(fl.isStatic()){
            fl.getVariables().forEach(v->arg.get(Constantes.LINT_REG_005)
                    .add(new NameWrapper(v.getName(),Constantes.LINT_REG_005)));

        }else {
            fl.getVariables().forEach(v->arg.get(Constantes.LINT_REG_004)
                    .add(new NameWrapper(v.getName(),Constantes.LINT_REG_004)));
        }
        fl.getVariables().forEach(v->arg.get(Constantes.LINT_REG_013)
                .add(new NameWrapper(v.getName(),Constantes.LINT_REG_013).addAccessSpec(fl.getAccessSpecifier().asString())));
        super.visit(fl,arg);
    }
    public void initializeKeyOfMap(Map<String, List<NameWrapper>> m,String... rules){
            for (String rule : rules) m.putIfAbsent(rule,new ArrayList<>());
    }
}
