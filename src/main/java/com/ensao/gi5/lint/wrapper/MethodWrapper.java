package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Utils;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.stmt.Statement;


public class MethodWrapper extends AbstractWrapper {
    private final String methodName;
    private final NodeList<Statement> statements = new NodeList<>();
    private final String className;

    public MethodWrapper(MethodDeclaration methodDeclaration) {
        super(methodDeclaration.getBegin().map(p -> p.line).orElse(-1));
        this.methodName = methodDeclaration.getNameAsString();
        this.className = Utils.getTypeNameFromNodes(methodDeclaration);
        methodDeclaration
                .getBody()
                .ifPresent(blockStmt -> statements.addAll(blockStmt.getStatements()));
    }

    public NodeList<Statement> getStatements() {
        return this.statements;
    }

    @Override
    public String toString() {
        return "Method '" + this.methodName + "' of Class '" + this.className + "' ";
    }
}
