package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

/*
    - This class is used to implement "rule number 8" which ensures that the number of lines in a method should never exceed 30 lines.
    - This class needs to extend the "Rule" class so that we can instantiate a new rule which is rule number 8.
    - To implement the rule, we created the method "apply" which iterates over each method in the specified file's compilation unit and checks if any of them
      exceeds the row count limit. If so, it creates a new instance of "Violation" with a description so that we can increment the number of violations observed.
    - To guarantee that the rule is always active we need to override the method "isActive" that should always return true to indicate that the rule is active.
*/

public class MethodBody extends Rule{
    public MethodBody() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        for (MethodDeclaration method : compilationUnit.getMethods()) {
            int methodLines = method.getEnd().get().line - method.getBegin().get().line;
            if (methodLines > 30) {
                Violation vio = new Violation();
                vio.setDescription("Method " + method.getNameAsString() + " has more than 30 lines.");
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
