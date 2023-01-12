package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.RuleWrapper;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class AttributesVisibilityVisitor extends VoidVisitorAdapter<List<RuleWrapper>> {
    private List<RuleWrapper> attributes ;
    public AttributesVisibilityVisitor(List<RuleWrapper> attributes){
        this.attributes=attributes;
    }
    @Override
    public void visit(FieldDeclaration n, List<RuleWrapper> arg) {
        super.visit(n, arg);
        n.getVariables().forEach(v -> {
            if (n.getModifiers().size()==0) {
                attributes.add(new RuleWrapper(v.getNameAsString(), n.getBegin().get().line));
            }
        });
    }
}
