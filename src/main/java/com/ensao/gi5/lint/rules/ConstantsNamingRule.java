package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.TypeDeclaration;

import java.util.List;

public class ConstantsNamingRule extends Rule{
    protected ConstantsNamingRule() {
        super(Constantes.LINT_REG_005, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        final List<TypeDeclaration<?>> types = compilationUnit.getTypes();

        types.stream()
                .forEach(type -> {
                    type.getFields().stream()
                            .filter(field -> field.isStatic() && field.isFinal() && !field.getVariables().get(0).getNameAsString().matches("^[A-Z_]+$"))
                            .forEach(field -> {
                                String message = "The constant " + field.getVariables().get(0).getNameAsString() + " must be in uppercase and separated with _ .";
                                final Violation violation = new Violation();
                                violation.setDescription(message);
                                violation.setFileName(compilationUnit.getFileName());
                                violation.setLine(type.getName().getBegin().get().line);
                                addViolation(violation);
                            });
                });

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
