package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;


public class ParamsNumberRule extends Rule {
    public ParamsNumberRule() {
        super(Constantes.LINT_REG_012, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.getMethods().forEach(method -> {
            if (method.getParameters().size() > 2) {
                Violation violance = new Violation();
                violance.setDescription("Method "+ method.getNameAsString() +  " has more than 2 parameters");
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
