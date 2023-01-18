package com.ensao.gi5.lint.visitor;
import com.ensao.gi5.lint.wrapper.ConstantsWrapper;
import com.ensao.gi5.lint.wrapper.EnumerationWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import java.util.Set;

public class EnumerationVisitor extends VoidVisitorAdapter<Set<EnumerationWrapper>> {
    @Override
    public void visit(FieldDeclaration fieldDeclaration, Set<EnumerationWrapper> arg){
        if((fieldDeclaration.isStatic() && fieldDeclaration.isFinal()))
            arg.add(new EnumerationWrapper(fieldDeclaration));
        super.visit(fieldDeclaration, arg);
    }
}