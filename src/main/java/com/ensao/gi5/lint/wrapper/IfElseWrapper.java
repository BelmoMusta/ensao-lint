package com.ensao.gi5.lint.wrapper;

import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;



public class IfElseWrapper {
    private final String name;
    private final int line;
    private final boolean hasBraces;

    public IfElseWrapper(IfStmt ifStmt) {
        this.name = "if";
        this.line = ifStmt.getBegin().map(p->p.line).orElse(-1);
        this.hasBraces = ifStmt.toString().contains("{}");
    }

    public IfElseWrapper(IfStmt ifStmt, String name) {
        Statement elseStmt = ifStmt.getElseStmt().orElse(null);
        this.name = name;
        if(elseStmt!=null) {
            this.line = elseStmt.getBegin().map(p->p.line).orElse(-1);
            this.hasBraces = elseStmt.toString().contains("{}");
        }else {
            this.line = -1;
            this.hasBraces = false;
        }
    }
    public String getName() {
        return name;
    }
    public int getLine() {
        return line;
    }

    public boolean hasBraces() {
        return hasBraces;
    }
}
