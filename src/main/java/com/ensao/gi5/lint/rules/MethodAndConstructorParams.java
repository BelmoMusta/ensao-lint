package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodAndConstructorParams extends Rule{

    protected MethodAndConstructorParams() {
        super(Constantes.LINT_REG_012, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(MethodDeclaration n, Void arg) {
                if (n.getParameters().size() > 2) {
                    final Violation violation = new Violation();
                    violation.setDescription("Too many parameters in method");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(n.getBegin().get().line);
                    addViolation(violation);
                }
                super.visit(n, arg);
            }

            @Override
            public void visit(ConstructorDeclaration n, Void arg) {
                if (n.getParameters().size() > 2) {
                    final Violation violation = new Violation();
                    violation.setDescription("Too many parameters in constructor");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(n.getBegin().get().line);
                    addViolation(violation);
                }
                super.visit(n, arg);
            }
        }, null);
    }


    @Override
    public boolean isActive() {
        return true;
    }
}
