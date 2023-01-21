package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.MethodeWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.Set;


public class argsSizeVisitors  extends VoidVisitorAdapter<Set<MethodeWrapper>> {
    
     @Override
    public void visit(MethodDeclaration classOrInterfaceType, Set<MethodeWrapper> arg) {
        arg.add(new MethodeWrapper(classOrInterfaceType.getNameAsString()));
        super.visit(classOrInterfaceType, arg);
    }
    
}
