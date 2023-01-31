package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.TypeDeclaration;

import java.util.List;

public class MethodsNumberRule extends Rule{
    protected MethodsNumberRule(String id, Level level) {
        super(Constantes.LINT_REG_011, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnitWrapper) {
        List<TypeDeclaration<?>> types = compilationUnitWrapper.getTypes();
        for (TypeDeclaration<?> type : types) {
            int methodCount = (int) type.getMethods().stream()
                    .filter(bodyDeclaration -> bodyDeclaration.isMethodDeclaration())
                    .count();
            if (methodCount > 20) {
                String message = "The class " + type.getNameAsString() + " has more than 20 methods declared.";
                final Violation violation = new Violation();
                violation.setDescription(message);
                violation.setFileName(compilationUnitWrapper.getFileName());
                violation.setLine(type.getName().getBegin().get().line);
                addViolation(violation);
            }
        }
    }


    @Override
    public boolean isActive() {
        return true;
    }
}
