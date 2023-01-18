package com.ensao.gi5.lint.wrapper;


import com.github.javaparser.ast.body.MethodDeclaration;

public class LinesWrapper {
    private final String name;
    private final int line;
    private final int startLine;
    private final int endLine;

    public LinesWrapper(MethodDeclaration methodDeclaration) {
        this.name = methodDeclaration.getNameAsString();
        this.line = methodDeclaration.getBegin().map(p->p.line).orElse(-1);
        this.startLine = methodDeclaration.getBegin().map(p->p.line).orElse(-1);
        this.endLine = methodDeclaration.getEnd().map(p->p.line).orElse(-1);
    }

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }

    public int getStartLine() {
        return startLine;
    }

    public int getEndLine() {
        return endLine;
    }
}
