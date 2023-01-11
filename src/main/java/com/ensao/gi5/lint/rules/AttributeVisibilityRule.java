package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.enumeration.Level;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.AttributeVisitor;
import com.ensao.gi5.lint.wrapper.AttributeWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AttributeVisibilityRule extends Rule{

    public AttributeVisibilityRule() {
        super(Constantes.LINT_REG_013, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<AttributeWrapper> attributes = new ArrayList<>();
        compilationUnit.accept(new AttributeVisitor(), attributes);
        attributes = attributes
                .stream()
                .filter(attribute -> attribute.getAccessModifier().isEmpty())
                .collect(Collectors.toList());
        for (AttributeWrapper attribute: attributes) {
            final Violation violation = violationBuilder
                    .withDescription(attribute + " should have an access modifier declared")
                    .withFileName(compilationUnit.getFileName())
                    .withLine(attribute.getLine())
                    .build();
            addViolation(violation);
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
