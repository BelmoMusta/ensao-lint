package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.LocalVariablesNameVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.LocalVariableNameWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

public class LocalVariableNameRule extends Rule{
    public LocalVariableNameRule() {
        super(Constantes.LINT_REG_003, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        Set<LocalVariableNameWrapper> allLocalVariables = new LinkedHashSet<>();
        compilationUnit.accept(new LocalVariablesNameVisitor(), allLocalVariables);
        allLocalVariables.stream().filter(localVar->!localVar.getName().matches("^[a-z].*")).forEach(localVar->{
            final Violation violation = new Violation();
            violation.setDescription("Variable '" + localVar.getName() + "' does not start with lowercase !");
            violation.setFileName(compilationUnit.getFileName());
            violation.setLine(localVar.getLine());
            addViolation(violation);
        });

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
