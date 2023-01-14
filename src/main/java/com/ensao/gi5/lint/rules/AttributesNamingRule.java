package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

import java.util.List;

public class AttributesNamingRule extends Rule{

    protected AttributesNamingRule(String id, Level level) {
        super(Constantes.LINT_REG_004, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        final List<TypeDeclaration<?>> types = compilationUnit.getTypes();
        types.stream()
                .forEach(type -> {
                    type.getMembers().stream()
                            .filter(member -> member instanceof FieldDeclaration && !((FieldDeclaration) member).isFinal())
                            .map(member -> (FieldDeclaration) member)
                            .forEach(field -> {
                                field.getVariables().stream()
                                        .filter(variable -> !Character.isLowerCase(variable.getNameAsString().charAt(0)))
                                        .forEach(variable -> {
                                            String message = "attribute " + variable.getNameAsString() + " must start with lowercase letter.";
                                            final Violation violation = new Violation();
                                            violation.setDescription(message);
                                            violation.setFileName(compilationUnit.getFileName());
                                            violation.setLine(field.getBegin().get().line);
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
