package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.LocalVariablesNameVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.LocalVariableNameWrapper;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 *The LocalVariableNameRule class is a Java class that extends the Rule abstract class.
 * It is used to check the naming convention of local variables within a compilation unit.
 *
 * The apply method is an implementation of the abstract method in the Rule class.
 * It uses the LocalVariablesNameVisitor class to traverse the compilation unit and
 * collect all local variables in a set. It then filters the set of variables by checking
 * if the name of the variable starts with a lowercase letter using regular expression.
 * If a variable does not follow this naming convention, a violation is created using the
 * ViolationFactory class and added to the violations list using the addViolation method.
 *
 *This class is a rule that checks the naming convention of local variables within a compilation unit,
 *  it will return a violation if the local variable's name does not start with a lowercase letter.
 *
 * **/
public class LocalVariableNameRule extends Rule{
    public LocalVariableNameRule() {
        super(Constantes.LINT_REG_003, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        Set<LocalVariableNameWrapper> allLocalVariables = new LinkedHashSet<>();
        compilationUnit.    accept(new LocalVariablesNameVisitor(), allLocalVariables);
        allLocalVariables.stream().filter(localVar->!localVar.getName().matches("^[a-z].*")).forEach(localVar->{
            final Violation violation = ViolationFactory.ViolationFactory(
                    compilationUnit.getFileName(),
                    "Variable '" + localVar.getName() + "' does not start with lowercase !!",
                    localVar.getLine());
            addViolation(violation);
        });

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
