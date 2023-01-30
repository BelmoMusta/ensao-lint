package com.ensao.gi5.lint.wrapper;

import com.ensao.gi5.lint.util.Utils;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.expr.ClassExpr;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;

import java.util.Objects;

public class NominationWrapper {
    private final String nominationFQN;
    private final String nomination;
    private final int line;


    public NominationWrapper(ClassExpr nomination) {
        this.nominationFQN = nomination.getTypeAsString();
        this.nomination = Utils.convertFQNToSimpleClassName(nomination.getTypeAsString());
        this.line = nomination.getBegin().map(p ->p.line).orElse(-1);
    }

    public NominationWrapper(String nomination) {
        this.nominationFQN = nomination;
        this.nomination = Utils.convertFQNToSimpleClassName(nomination);
        this.line = 0;
    }

    public NominationWrapper(NameExpr name) {
        this(name.getNameAsString());
    }

    public NominationWrapper(Name name) {
        this(name.asString());
    }

    public NominationWrapper(SimpleName name) {
        this(name.asString());
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ImportWrapper)) {
            return false;
        }
        NominationWrapper that = (NominationWrapper) o;
        return Objects.equals(nomination, that.nomination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomination);
    }

    @Override
    public String toString() {
        return nominationFQN;
    }

    public int getLine() {
        return line;
    }
}
