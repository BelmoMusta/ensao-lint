package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ConstantVariablesOfClassesVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ConstantVariablesOfClassesWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

/*
 *This class is a rule that checks the names of CONSTANT variables within a compilation unit,
 * It will return you a violation if the name of a constant variable is not written with Uppercase letters and separated with _ .
 */

// This class extends from an abstract class Rule
//We did the implementation of the abstract methods in the Rule class apply and isActive

public class ConstantVariablesOfClassesRule extends Rule{

    public ConstantVariablesOfClassesRule() {
        super(Constantes.LINT_REG_005, Level.MEDIUM);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        // It collects the existing local variables in a set
        // Then it filters it by checking if the name of the variable matches the regex
        Set<ConstantVariablesOfClassesWrapper> existingClassConstants = new LinkedHashSet<>();
        compilationUnit.accept(new ConstantVariablesOfClassesVisitors(), existingClassConstants);

        //Actually same logic if we want to do rule 3 we just need in case to see the match for this regex "[a-z]\w*"

        existingClassConstants.stream().filter(constant->!constant.getName().matches("[A-Z_]+")).forEach(cst->{
            //If it doesnt match the regex it adds a violation
            final Violation violation = new Violation();
            violation.setDescription("Constant variable " + cst.getName() + " is not all with uppercase letters and is not separated with _ ");
            violation.setFileName(compilationUnit.getFileName());
            violation.setLine(cst.getLine());
            addViolation(violation);
        });
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
