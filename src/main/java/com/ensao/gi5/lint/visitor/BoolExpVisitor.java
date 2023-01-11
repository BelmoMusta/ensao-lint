package com.ensao.gi5.lint.visitor;

import java.util.List;
import java.util.regex.Pattern;

import com.ensao.gi5.lint.wrapper.StmtWrapper;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class BoolExpVisitor extends VoidVisitorAdapter<List<StmtWrapper>>  {
	
    @Override
    public void visit(BlockStmt blockStmt, List<StmtWrapper> arg) {

    	blockStmt.getStatements().stream().filter(s -> Pattern.compile("==|!=|<|>|>=|<=")
                .matcher(s.toString()).find()).forEach(s ->
                    arg.add(new StmtWrapper(s))
        );
        super.visit(blockStmt, arg);
    }

}
