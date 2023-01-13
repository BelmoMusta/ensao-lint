package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.util.Method;
import com.ensao.gi5.lint.wrapper.ClassWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.expr.SimpleName;


import java.util.List;

public class ClassVisitor  extends VoidVisitorAdapter<List<ClassWrapper>> {
    @Override
    public void visit(ClassOrInterfaceDeclaration n, List<ClassWrapper> arg) {
        ClassWrapper classWrapper = new ClassWrapper(n.getNameAsString(), getLine(n.getName()));

        n.getMethods().forEach(m ->{
            Method method = new Method(m.getNameAsString());

            int lineCount = m.getEnd().map(p->p.line).orElse(0) - m.getBegin().map(p->p.line).orElse(0);
            method.setLinesCount(lineCount);
        });
        arg.add(classWrapper);
        super.visit(n, arg);
    }
    public int getLine(SimpleName simpleName){
        return simpleName.getBegin().map(p -> p.line).orElse(-1);
    }

}
