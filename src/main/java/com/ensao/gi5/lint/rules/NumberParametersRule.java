package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;


public class NumberParametersRule extends Rule {
    public NumberParametersRule() {
        super(Constantes.LINT_REG_012, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        for (MethodDeclaration method : compilationUnit.getMethods()) {
            if (method.getParameters().size() > 2) {
                Violation vio = new Violation();
                vio.setDescription("Method "+ method.getNameAsString() +  " has more than 2 parameters");
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
