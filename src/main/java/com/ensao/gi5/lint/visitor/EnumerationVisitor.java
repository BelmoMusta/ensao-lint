package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.rules.EnumerationRule;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.wrapper.Element;
import com.ensao.gi5.lint.wrapper.EnumerationWrapper;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;
import java.util.stream.Collectors;

public class EnumerationVisitor extends VoidVisitorAdapter<List<EnumerationWrapper>> {

    @Override
    public void visit(EnumDeclaration enumDeclaration, List<EnumerationWrapper> arg) {
        arg.add(new EnumerationWrapper(enumDeclaration.getNameAsString(), enumDeclaration.getEntries().stream()
                        .map(e -> new Element(e.getNameAsString(), Utils.getLine(e.getName()))).collect(Collectors.toList())));
        super.visit(enumDeclaration, arg);
    }

}
