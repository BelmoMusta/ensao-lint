package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ClassVisitor;
import com.ensao.gi5.lint.wrapper.ClassWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.ArrayList;
import java.util.List;

public class MethodRule extends Rule {
    public  MethodRule(){
        super(Constantes.LINT_REG_011, Level.HIGHEST);

    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnitWrapper) {
        List<ClassWrapper> classes = new ArrayList<>();
        compilationUnitWrapper.accept(new ClassVisitor(), classes);

        for(ClassWrapper classe : classes) {
            if (classe.getMethods().size() > 20) {
                final Violation violation = new Violation();
                violation.setDescription("Methods of the classe should be less than 20 " );
                violation.setFileName(compilationUnitWrapper.getFileName());
                violation.setLine(classe.getLine());
                addViolation(violation);
            }
        }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
