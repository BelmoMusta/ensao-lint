package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.wrapper.ClassWrapper.ClassWrapper;
import com.ensao.gi5.lint.wrapper.ClassWrapper.Constructor;
import com.ensao.gi5.lint.wrapper.ClassWrapper.Method;
import com.ensao.gi5.lint.wrapper.ClassWrapper.Parameter;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


import java.util.List;
import java.util.regex.Pattern;

public class ClassVisitor extends VoidVisitorAdapter<List<ClassWrapper>> {

    @Override
    public void visit(ClassOrInterfaceDeclaration n, List<ClassWrapper> arg) {
        ClassWrapper classWrapper = new ClassWrapper(n.getNameAsString(), Utils.getLine(n.getName()));

        //adding classes constructors
        n.getConstructors().forEach(c -> {
            Constructor constructor = new Constructor(c.getNameAsString(), Utils.getLine(c.getName()));
            c.getParameters().forEach(p -> {
                constructor.getParameters().add(new Parameter(p.getTypeAsString(), p.getNameAsString()));
            });
            classWrapper.getConstructors().add(constructor);
        });

        //adding classes methods
        n.getMethods().forEach(m ->{
            Method method = new Method(m.getNameAsString(), m.getTypeAsString(),
                    m.getAccessSpecifier().asString(), m.getBegin().map(p->p.line).orElse(-1));

            m.getParameters().forEach(p -> method.getParameters().add(new Parameter(p.getTypeAsString(), p.getNameAsString())));
            int lineCount = m.getEnd().map(p->p.line).orElse(0) - m.getBegin().map(p->p.line).orElse(0);
            method.setLinesCount(lineCount);
            m.getBody().ifPresent(e -> method.setReturnCount((int) e.getStatements().stream().filter(Statement::isReturnStmt).count()));
            m.getBody().ifPresent(e -> method.setThrowCount((int) e.getStatements().stream().filter(Statement::isThrowStmt).count()));
            String pattern = m.getName() +"\\s*\\(([\\w.]+\\s*[,]?\\s*){"+ m.getParameters().size() +"}\\);";
            method.setUsed(Pattern.compile(pattern).matcher(n.toString()).find());
            classWrapper.getMethods().add(method);
        });
        arg.add(classWrapper);
        super.visit(n, arg);
    }

}
