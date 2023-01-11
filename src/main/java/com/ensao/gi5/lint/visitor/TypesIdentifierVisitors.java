
package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.TypesWraper;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.Set;


public class TypesIdentifierVisitors extends VoidVisitorAdapter<Set<TypesWraper>> {
    
     @Override
    public void visit(ClassOrInterfaceType classOrInterfaceType, Set<TypesWraper> arg) {
        arg.add(new TypesWraper(classOrInterfaceType.getName()));
        super.visit(classOrInterfaceType, arg);
    }
    
}
