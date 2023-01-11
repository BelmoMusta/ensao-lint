package com.ensao.gi5.lint.visitor;

import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.Hashtable;
import java.util.List;

public class EnumerationVisitor extends VoidVisitorAdapter<Void> {
    private Hashtable<String,Integer> enumerationNaming ;
    public EnumerationVisitor(Hashtable<String,Integer> enumerationNaming){
        this.enumerationNaming = enumerationNaming;
    }
    @Override
    public void visit(EnumDeclaration n, Void arg) {
        super.visit(n, arg);
        List<EnumConstantDeclaration> constants = n.getEntries();
        for (EnumConstantDeclaration constant : constants) {
            String name = constant.getNameAsString();
            if (!name.equals(name.toUpperCase()) || !name.contains("_")) {
                enumerationNaming.put(name,constant.getRange().isPresent()?constant.getRange().get().begin.line:0);
            }
        }
    }
}
