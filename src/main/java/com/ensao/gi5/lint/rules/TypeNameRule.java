package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.enumeration.Level;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.visitor.BooleanExpressionVisitor;
import com.ensao.gi5.lint.visitor.TypeVisitor;
import com.ensao.gi5.lint.wrapper.BooleanExpressionWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.TypeWrapper;

import java.util.ArrayList;
import java.util.List;

public class TypeNameRule extends Rule {
    public TypeNameRule() {
        super(Constantes.LINT_REG_002, Level.HIGHEST);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<TypeWrapper> typeNames = new ArrayList<>();
        List<BooleanExpressionWrapper> values = new ArrayList<>();
        compilationUnit.accept(new BooleanExpressionVisitor(), values);
        compilationUnit.accept(new TypeVisitor(), typeNames);
        typeNames = Utils.checkIfNamesDontFollowsRule(typeNames);
        for (TypeWrapper typeName: typeNames) {
            final Violation violation = violationBuilder
                    .withDescription(typeName + " should start with uppercase and shouldn't contain underscores")
                    .withFileName(compilationUnit.getFileName())
                    .withLine(typeName.getLine())
                    .build();
            addViolation(violation);
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
