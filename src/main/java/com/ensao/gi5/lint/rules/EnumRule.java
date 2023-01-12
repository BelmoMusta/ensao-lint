package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class EnumRule extends Rule{
    public EnumRule() {
        super(Constantes.LINT_REG_007, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(EnumDeclaration n, Void arg) {
                for (EnumConstantDeclaration enumConstant : n.getEntries()) {
                    String name = enumConstant.getNameAsString();
                    if (!name.equals(name.toUpperCase()) || name.contains("_")) {
                        final Violation violation = new Violation();
                        violation.setDescription("Enumeration elements must be in uppercase and use _ as separator");
                        violation.setFileName(compilationUnit.getFileName());
                        violation.setLine(enumConstant.getBegin().get().line);
                        addViolation(violation);
                    }
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
