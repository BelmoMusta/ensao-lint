package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.List;

public class MethodsNumPerClassRule extends Rule {

    private int maxMethodsPerClass = 20;

    public MethodsNumPerClassRule() {
        super(Constantes.LINT_REG_011, Level.HIGHEST);
    }


    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        final List<MethodDeclaration> methodDeclarations = compilationUnit.getMethods();
        if (methodDeclarations.size() > this.maxMethodsPerClass) {
            MethodDeclaration md = methodDeclarations.get(21);
            final Violation violation = new Violation();
            violation.setDescription("Max number of methods per class is 20");
            violation.setFileName(compilationUnit.getFileName());
            violation.setLine(md.getBody().get().getBegin().get().line);
            addViolation(violation);

        }


    }

    @Override
    public boolean isActive() {
        return true;
    }
}
