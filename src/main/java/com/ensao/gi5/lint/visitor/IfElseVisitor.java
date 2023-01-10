package com.ensao.gi5.lint.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.wrapper.StmtWrapper;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class IfElseVisitor extends VoidVisitorAdapter<Map<String, List<StmtWrapper>>>  {
	
    @Override
    public void visit(IfStmt n, Map<String, List<StmtWrapper>> arg) {
    	
    	arg.putIfAbsent(Constantes.LINT_REG_006, new ArrayList<>());
        arg.get(Constantes.LINT_REG_018).add(new StmtWrapper(Constantes.LINT_REG_018, n.getThenStmt()));
        n.getElseStmt().ifPresent(s -> arg.get(Constantes.LINT_REG_018)
        		.add(new StmtWrapper(Constantes.LINT_REG_018, s)));
        super.visit(n, arg);
    }
}
