package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodBodyRule extends Rule{
    public MethodBodyRule() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(MethodDeclaration n, Void arg) {
                if (n.getBody().isPresent()) {
                    int lines = n.getBody().get().toString().split("\r\n|\r|\n").length;
                    if (lines > 30) {
                        final Violation violation = new Violation();
                        violation.setDescription("Method body should not exceed 30 lines");
                        violation.setFileName(compilationUnit.getFileName());
                        violation.setLine(n.getBegin().get().line);
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
