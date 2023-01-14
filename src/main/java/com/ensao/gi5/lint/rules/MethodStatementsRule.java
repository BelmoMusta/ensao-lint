package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.enumeration.Level;
import com.ensao.gi5.lint.visitor.MethodVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.MethodWrapper;

import java.util.ArrayList;
import java.util.List;

public class MethodStatementsRule extends Rule {

    public MethodStatementsRule() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<MethodWrapper> methods = new ArrayList<>();
        compilationUnit.accept(new MethodVisitor(), methods);
        for (MethodWrapper method: methods) {
            if (checkIfMethodHasMoreThanThirtyLines(method)) {
                buildViolationThenAddToCollection(
                        method.getLine(),
                        method + "shouldn't contain more than 30 statements",
                        compilationUnit.getFileName()
                );
            }
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
