package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.wrapper.Element;
import com.ensao.gi5.lint.wrapper.EnumWrapper;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;
import java.util.stream.Collectors;

public class EnumVisitor extends VoidVisitorAdapter<List<EnumWrapper>> {

    @Override
    public void visit(EnumDeclaration enumDeclaration , List<EnumWrapper> arg){
        arg.add(new EnumWrapper(enumDeclaration.getNameAsString(),enumDeclaration.getEntries()
                .stream()
                .map(i->new Element(i.getNameAsString(), Utils.getLine(i.getName())))
                .collect(Collectors.toList())));
        super.visit(enumDeclaration,arg);
    }
}
