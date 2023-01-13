package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.wrapper.StatementWrapper;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class StatementVisitor extends VoidVisitorAdapter<Map<String, List<StatementWrapper>>> {

    @Override
    public void visit(LambdaExpr lambdaExpr, Map<String, List<StatementWrapper>> arg) {





    }

    @Override
    public void visit(BlockStmt blockStmt, Map<String, List<StatementWrapper>> arg) {

        init(Constantes.LINT_REG_006, arg);

        blockStmt.getStatements().stream().filter(s -> Pattern.compile("==|!=|<|>|>=|<=")
                .matcher(s.toString()).find()).forEach(s ->
                arg.get(Constantes.LINT_REG_006).add(new StatementWrapper(Constantes.LINT_REG_006, s))
        );

        super.visit(blockStmt, arg);
    }

    protected void init(String ruleId, Map<String, List<StatementWrapper>> mapStatements) {

        mapStatements.putIfAbsent(ruleId, new ArrayList<>());
    }


}
