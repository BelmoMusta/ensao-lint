package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.LocalVariablesVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.LocalVariablesWrapper;

import java.util.LinkedHashSet;
import java.util.Set;
/*
 *This class is a rule that checks the names of local variables within a compilation unit,
 * It will return you a violation if the name of a local variable does not start with a lowercase letter.
 */

// This class extends from an abstract class Rule

public class LocalVariablesRule extends Rule{

    public LocalVariablesRule() {
        super(Constantes.LINT_REG_003, Level.HIGH);
    }
    //We did the implementation of the abstract methods in the Rule class apply and isActive

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        // It collects the existing local variables in a set
        final Set<LocalVariablesWrapper> existingLocalVariables = new LinkedHashSet<>();
        compilationUnit.accept(new LocalVariablesVisitors(), existingLocalVariables);
        // Then it filters it by checking if the name of the variable matches the regex

        existingLocalVariables.stream().filter(Var->!Var.getName().matches("^[a-z].*")).forEach(Var->{
            //If not it adds a violation
            final Violation violation = new Violation();
            violation.setDescription("Local Variable " + Var.getName() + " should start with a lowercase letter");
            violation.setFileName(compilationUnit.getFileName());
            violation.setLine(Var.getLine());
            addViolation(violation);
        });
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
