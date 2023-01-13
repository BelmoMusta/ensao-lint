package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ClassAttributsNameVisitor;
import com.ensao.gi5.lint.visitor.ClassConstantsNameVisitor;
import com.ensao.gi5.lint.wrapper.ClassAttributsNameWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class ClassConstantsNameRule extends Rule{
    public ClassConstantsNameRule() {
        super(Constantes.LINT_REG_005, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<ClassAttributsNameWrapper> allClassConstants = new LinkedHashSet<>();
        compilationUnit.accept(new ClassConstantsNameVisitor(), allClassConstants);
        allClassConstants.stream().filter(attribute->!attribute.getName().matches("^[A-Z0-9_]*")).forEach(att->{
            final Violation violation = new Violation();
            violation.setDescription("La constante '"+att.getName() +"' de la class '"+att.getClassName()+"' it's not all Uppercase !");
            violation.setFileName(compilationUnit.getFileName());
            violation.setLine(att.getLine());
            addViolation(violation);
        });
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
