package com.ensao.gi5.lint.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.wrapper.StmtWrapper;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class BoolExpVisitor extends VoidVisitorAdapter<Map<String, List<StmtWrapper>>>  {
	
    @Override
    public void visit(BlockStmt n, Map<String, List<StmtWrapper>> arg) {

    	arg.putIfAbsent(Constantes.LINT_REG_006, new ArrayList<>());
        n.getStatements().stream().filter(s -> Pattern.compile("==|!=|<|>|>=|<=")
                .matcher(s.toString()).find()).forEach(s ->
                    arg.get(Constantes.LINT_REG_006).add(new StmtWrapper(Constantes.LINT_REG_006, s))
        );
        super.visit(n, arg);
    }

}
