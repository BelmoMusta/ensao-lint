package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

import java.util.List;

public class LocalVariablesNamingRule extends Rule {
    protected LocalVariablesNamingRule() {
        super(Constantes.LINT_REG_003, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        final List<TypeDeclaration<?>> types = compilationUnit.getTypes();
        types.stream()
                .forEach(type -> {
                    type.findAll(MethodDeclaration.class)
                            .stream()
                            .forEach(method -> {
                                method.findAll(VariableDeclarator.class)
                                        .stream()
                                        .filter(var -> !Character.isLowerCase(var.getNameAsString().charAt(0)))
                                        .forEach(var -> {
                                            String message = "The local variable " + var.getNameAsString() + " must start with lowercase letter.";
                                            final Violation violation = new Violation();
                                            violation.setDescription(message);
                                            violation.setFileName(compilationUnit.getFileName());
                                            violation.setLine(var.getName().getBegin().get().line);
                                            addViolation(violation);
                                        });
                            });
                });
    }


    @Override
    public boolean isActive() {
        return true;
    }
}
