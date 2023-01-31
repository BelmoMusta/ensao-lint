package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ClassAttributesVisitor;
import com.ensao.gi5.lint.visitor.ConstantesAttributesVisitor;
import com.ensao.gi5.lint.wrapper.ClassAttributesWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class ConstantesAttributesRule extends Rule{


    public ConstantesAttributesRule(){
        super(Constantes.LINT_REG_005, Level.MEDIUM);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<ClassAttributesWrapper> constantesAttributesWrapperSet = new LinkedHashSet<ClassAttributesWrapper>();
        compilationUnit.accept(new ConstantesAttributesVisitor(),constantesAttributesWrapperSet);

        constantesAttributesWrapperSet.stream()
                .filter(field -> !field.toString().matches("^[A-Z][A-Z_]*"))
                .forEach(field -> {
                    final Violation violation = new Violation();
                    violation.setDescription(field.toString() + "n'est pas en majuscule'");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(field.getLine());
                    addViolation(violation);
                });
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
