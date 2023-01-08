package com.ensao.gi5.lint.visitor;


import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.wrapper.StatementWrapper;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class StatementVisitor extends VoidVisitorAdapter<Map<String, List<StatementWrapper>>> {



    @Override
    public void visit(LambdaExpr n, Map<String, List<StatementWrapper>> arg) {
        initKey(Constantes.LINT_REG_010, arg);
        arg.get(Constantes.LINT_REG_010).add(new StatementWrapper(Constantes.LINT_REG_010, n.getBody()));
        super.visit(n, arg);
    }

    @Override
    public void visit(BlockStmt n, Map<String, List<StatementWrapper>> arg) {
        initKey(Constantes.LINT_REG_009, arg);
        initKey(Constantes.LINT_REG_006, arg);
        n.getStatements().stream().filter(s -> s.toString().contains("new")).forEach(s -> {
            arg.get(Constantes.LINT_REG_009).add(
                    new StatementWrapper(Constantes.LINT_REG_009, s)
            );
        });

        n.getStatements().stream().filter(s -> Pattern.compile("==|!=|<|>|>=|<=")
                .matcher(s.toString()).find()).forEach(s ->
                    arg.get(Constantes.LINT_REG_006).add(new StatementWrapper(Constantes.LINT_REG_006, s))
        );
        super.visit(n, arg);
    }

    @Override
    public void visit(IfStmt n, Map<String, List<StatementWrapper>> arg) {
        initKey(Constantes.LINT_REG_018, arg);
        arg.get(Constantes.LINT_REG_018).add(new StatementWrapper(Constantes.LINT_REG_018, n.getThenStmt()));
        n.getElseStmt().ifPresent(s -> arg.get(Constantes.LINT_REG_018).add(new StatementWrapper(Constantes.LINT_REG_018, s)));
        super.visit(n, arg);
    }

    @Override
    public void visit(CatchClause n, Map<String, List<StatementWrapper>> arg) {
        initKey(Constantes.LINT_REG_015, arg);
        arg.get(Constantes.LINT_REG_015).add(new StatementWrapper(Constantes.LINT_REG_015, n.getBody()));
        super.visit(n, arg);
    }



    private void initKey(String ruleId, Map<String , List<StatementWrapper>> map){
        map.putIfAbsent(ruleId, new ArrayList<>());
    }

}
