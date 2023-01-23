package com.ensao.gi5.lint.rules;

import java.util.List;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

public class MethodLengthRule extends Rule {

    private static final int MAX_METHOD_LENGTH = 30;

    public MethodLengthRule() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<MethodDeclaration> methods = compilationUnit.getMethods();
        for (MethodDeclaration method : methods) {
            int methodLength = method.getEnd().get().line - method.getBegin().get().line + 1;
            if (methodLength > MAX_METHOD_LENGTH) {
                Violation violation = new Violation();
                violation.setDescription("Method '" + method.getNameAsString() + "' exceeds " + MAX_METHOD_LENGTH + " lines");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(method.getBegin().get().line);
                addViolation(violation);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}