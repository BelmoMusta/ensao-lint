package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

public class MethodBodyRule extends Rule{
    public MethodBodyRule() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.getMethods().forEach(method -> {
            int methodLines = method.getEnd().get().line - method.getBegin().get().line;
            if (methodLines > 30) {
                Violation violance = new Violation();
                violance.setDescription("Method " + method.getNameAsString() + " has more than 30 lines.");
                violance.setFileName(compilationUnit.getFileName());
                violance.setLine(method.getBegin().get().line);
                addViolation(violance);
            }
        });
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
