package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.wrapper.EnumWrapper.Element;
import com.ensao.gi5.lint.wrapper.EnumWrapper.EnumWrapper;

import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.List;
import java.util.stream.Collectors;

public class EnumVisitor extends VoidVisitorAdapter<List<EnumWrapper>> {

    @Override
    public void visit(EnumDeclaration n, List<EnumWrapper> arg) {
        arg.add(new EnumWrapper(n.getNameAsString(),
            n.getEntries().stream()
                .map(e -> new Element(e.getNameAsString(), Utils.getLine(e.getName()))).collect(Collectors.toList())));
        super.visit(n, arg);
    }
}
