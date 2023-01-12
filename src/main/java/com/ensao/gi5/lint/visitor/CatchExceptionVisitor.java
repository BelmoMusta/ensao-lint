package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.CatchExceptionWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class CatchExceptionVisitor extends VoidVisitorAdapter<List<CatchExceptionWrapper>> {
    @Override
    public void visit(MethodDeclaration n, List<CatchExceptionWrapper> arg) {
        super.visit(n, arg);
        n.findAll(CatchClause.class)
                .forEach(catchClause -> {
                    boolean isLogging = catchClause.getBody().getStatements().stream()
                            .anyMatch(stmt -> stmt.toString().contains("log"));
                    if (!isLogging) {
                        int line = n.getRange().isPresent()?n.getRange().get().begin.line:0;
                        arg.add(new CatchExceptionWrapper(n.getNameAsString(), catchClause.getParameter().getNameAsString(),line));
                    }
                });
    }
    }
