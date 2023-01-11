package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.enumeration.Level;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.MethodVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.MethodWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MethodLinesRule extends Rule {

    public MethodLinesRule() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<MethodWrapper> methods = new ArrayList<>();
        compilationUnit.accept(new MethodVisitor(), methods);
        methods = methods
                .stream()
                .filter(this::checkIfMethodHasMoreThanThirtyLines)
                .collect(Collectors.toList());
        for (MethodWrapper method: methods) {
            Violation violation = violationBuilder
                    .withDescription(method + "shouldn't contain more than 30 lines")
                    .withFileName(compilationUnit.getFileName())
                    .withLine(method.getLine())
                    .build();
            addViolation(violation);
        }
    }

    public boolean checkIfMethodHasMoreThanThirtyLines(MethodWrapper methodWrapper) {
        return methodWrapper.getStatements().size() > 30;
    }
    @Override
    public boolean isActive() {
        return true;
    }
}
