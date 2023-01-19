package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.GeneralStatementWrapper;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class CatchExceptionVisitor extends VoidVisitorAdapter<List<GeneralStatementWrapper>> {

    @Override
    public void visit(CatchClause catchClause,List<GeneralStatementWrapper> arg){
        arg.add(new GeneralStatementWrapper(catchClause.getBody()));
        super.visit(catchClause,arg);
    }

}
