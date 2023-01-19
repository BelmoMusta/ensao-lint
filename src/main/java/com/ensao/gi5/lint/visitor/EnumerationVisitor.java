package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RuleWrapper;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Hashtable;
import java.util.List;

public class EnumerationVisitor extends VoidVisitorAdapter<Void> {
    private List<RuleWrapper> enumerationNaming;
    public EnumerationVisitor(List<RuleWrapper> enumerationNaming){
        this.enumerationNaming = enumerationNaming;
    }
    @Override
    public void visit(EnumDeclaration n, Void arg) {
        super.visit(n, arg);
        List<EnumConstantDeclaration> enums = n.getEntries();
        for (EnumConstantDeclaration enumeration : enums) {
            String name = enumeration.getNameAsString();
            if (!name.equals(name.toUpperCase()) || !name.contains("_")) {
                enumerationNaming.add(new RuleWrapper(name,enumeration.getRange().isPresent()?enumeration.getRange().get().begin.line:0));
            }
        }
    }
}
