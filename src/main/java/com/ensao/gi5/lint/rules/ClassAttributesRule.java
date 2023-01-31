package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ClassAttributesVisitor;
import com.ensao.gi5.lint.visitor.LocalVariableVisitor;
import com.ensao.gi5.lint.wrapper.ClassAttributesWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.LocalVariableWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class ClassAttributesRule extends Rule{

    public ClassAttributesRule(){
        super(Constantes.LINT_REG_004,Level.HIGH);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        Set<ClassAttributesWrapper> classAttributesWrapperSet = new LinkedHashSet<ClassAttributesWrapper>();
        compilationUnit.accept(new ClassAttributesVisitor(),classAttributesWrapperSet);


        classAttributesWrapperSet.stream()
                .filter(field -> !Character.isLowerCase(field.toString().charAt(0)))
                .forEach(field -> {
                    final Violation violation = new Violation();
                    violation.setDescription(field.toString() + "n'est pas en miniscule'");
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
