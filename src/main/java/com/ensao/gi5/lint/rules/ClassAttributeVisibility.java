package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassAttributeVisibility extends Rule{
    public ClassAttributeVisibility() {
        super(Constantes.LINT_REG_013, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(FieldDeclaration n, Void arg) {
                if (n.getModifiers().isEmpty()) {
                    final Violation violation = new Violation();
                    violation.setDescription("Class attribute must have visibility declared");
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
