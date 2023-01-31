package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Constructor;
import com.ensao.gi5.lint.visitor.ClassVisitor;
import com.ensao.gi5.lint.wrapper.ClassWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.ArrayList;
import java.util.List;

public class ConstructorRule extends Rule{
    public ConstructorRule(){
        super(Constantes.LINT_REG_012, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<ClassWrapper> classes = new ArrayList<>();
        compilationUnit.accept(new ClassVisitor(), classes);

        for (ClassWrapper classe : classes) {
            for (Constructor constructor : classe.getConstructors()) {
                if (constructor.getParameters().size() > 2) {
                    final Violation violation = new Violation();
                    violation.setDescription("Parameters of the constructor should be less than 2");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(classe.getLine());
                    addViolation(violation);
                }

            }
        }
    }

            @Override
            public boolean isActive () {
                return true;
            }

    }
