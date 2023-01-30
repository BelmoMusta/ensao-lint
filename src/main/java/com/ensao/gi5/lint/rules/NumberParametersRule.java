package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

/*
    - This class is used to implement "rule number 12" which ensures that a method can not have more than two parameters.
    - This class needs to extend the "Rule" class so that we can instantiate a new rule which is rule number 12.
    - To implement the rule, we created the method "apply" which iterates over each method in the specified file's compilation unit and checks if any of them
      does take more than two parameters. If so, it creates a new instance of "Violation" with a description so that we can increment the number of violations observed.
    - To guarantee that the rule is always active we need to override the method "isActive" that should always return true to indicate that the rule is active.
*/

public class NumberParametersRule extends Rule {
    public NumberParametersRule() {
        super(Constantes.LINT_REG_012, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        for (MethodDeclaration method : compilationUnit.getMethods()) {
            if (method.getParameters().size() > 2) {
                Violation vio = new Violation();
                vio.setDescription("Method "+ method.getNameAsString() +  " has more than 2 parameters");
                vio.setFileName(compilationUnit.getFileName());
                vio.setLine(method.getBegin().get().line);
                addViolation(vio);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
