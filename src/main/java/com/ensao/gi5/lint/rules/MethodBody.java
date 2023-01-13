package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

public class MethodBody extends Rule{
    public MethodBody() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        for (MethodDeclaration method : compilationUnit.getMethods()) {
            int methodLines = method.getEnd().get().line - method.getBegin().get().line;
            if (methodLines > 30) {
                Violation vio = new Violation();
                vio.setDescription("Method " + method.getNameAsString() + " has more than 30 lines.");
                vio.setFileName(compilationUnit.getFileName());
                vio.setLine(method.getBegin().get().line);
                addViolation(vio);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
