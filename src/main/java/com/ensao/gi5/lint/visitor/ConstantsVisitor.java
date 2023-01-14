package com.ensao.gi5.lint.visitor;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.ensao.gi5.lint.wrapper.ConstantsWrapper;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.Set;

public class ConstantsVisitor extends VoidVisitorAdapter<Set<ConstantsWrapper>> {
    @Override
    public void visit(FieldDeclaration fieldDeclaration, Set<ConstantsWrapper> arg){
        if((fieldDeclaration.isStatic() && fieldDeclaration.isFinal()))
        arg.add(new ConstantsWrapper(fieldDeclaration));
        super.visit(fieldDeclaration, arg);
    }
}