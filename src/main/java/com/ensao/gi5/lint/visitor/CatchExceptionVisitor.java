package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RuleWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;
public class CatchExceptionVisitor extends VoidVisitorAdapter<List<RuleWrapper>> {
    private final List<RuleWrapper> exceptions;
    public CatchExceptionVisitor(List<RuleWrapper> exceptions){
        this.exceptions = exceptions;
    }
    @Override
    public void visit(MethodDeclaration n, List<RuleWrapper> arg) {
        super.visit(n, arg);
        n.findAll(CatchClause.class)
                .forEach(catchClause -> {
                    boolean isLogging = catchClause.getBody().getStatements().stream()
                            .anyMatch(stmt -> stmt.toString().contains("log"));
                    if (!isLogging) {
                        int line = n.getRange().isPresent()?n.getRange().get().begin.line:0;
                        exceptions.add(new RuleWrapper(catchClause.getParameter().getNameAsString(),line));
                    }
                });
    }
    }
