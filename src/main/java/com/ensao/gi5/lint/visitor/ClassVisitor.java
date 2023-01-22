package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.wrapper.ClassWrapper;
import com.ensao.gi5.lint.wrapper.Constructor;
import com.ensao.gi5.lint.wrapper.Method;
import com.ensao.gi5.lint.wrapper.Parameter;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class ClassVisitor extends VoidVisitorAdapter<List<ClassWrapper>> {

    @Override
    public void visit(ClassOrInterfaceDeclaration n, List<ClassWrapper> arg) {
        ClassWrapper classWrapper = new ClassWrapper(n.getNameAsString(), Utils.getLine(n.getName()));

        n.getConstructors().forEach(c -> {
            Constructor constructor = new Constructor(c.getNameAsString(), Utils.getLine(c.getName()));
            c.getParameters().forEach(p -> {
                constructor.getParameters().add(new Parameter(p.getTypeAsString(), p.getNameAsString()));
            });
            classWrapper.getConstructors().add(constructor);
        });

        n.getMethods().forEach(m ->{
            Method method = new Method(m.getNameAsString(), m.getTypeAsString(), m.getBegin().map(p->p.line).orElse(-1));
            m.getParameters().forEach(p -> method.getParameters().add(new Parameter(p.getTypeAsString(), p.getNameAsString())));
            int lineCount = m.getEnd().map(p->p.line).orElse(0) - m.getBegin().map(p->p.line).orElse(0);
            method.setLinesCount(lineCount);
            m.getBody().ifPresent(e -> method.setReturnCount((int) e.getStatements().stream().filter(Statement::isReturnStmt).count()));
            m.getBody().ifPresent(e -> method.setThrowCount((int) e.getStatements().stream().filter(Statement::isThrowStmt).count()));

            classWrapper.getMethods().add(method);
        });
        arg.add(classWrapper);
        super.visit(n, arg);
    }



}
