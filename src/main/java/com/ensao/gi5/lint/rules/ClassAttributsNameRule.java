package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ClassAttributsNameVisitor;
import com.ensao.gi5.lint.visitor.LocalVariablesNameVisitor;
import com.ensao.gi5.lint.wrapper.ClassAttributsNameWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.LocalVariableNameWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class ClassAttributsNameRule extends Rule{
    public ClassAttributsNameRule() {
        super(Constantes.LINT_REG_004, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<ClassAttributsNameWrapper> allClassAttributes = new LinkedHashSet<>();
        compilationUnit.accept(new ClassAttributsNameVisitor(), allClassAttributes);
        allClassAttributes.stream().filter(attribute->!attribute.getName().matches("^[a-z].*")).forEach(att->{
            final Violation violation = new Violation();
            violation.setDescription("L'attribut '"+att.getName() +"' de la class '"+att.getClassName()+"' Does not start with lowercase !");
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
