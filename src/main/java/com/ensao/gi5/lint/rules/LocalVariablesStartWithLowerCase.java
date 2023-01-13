package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LocalVariablesStartWithLowerCase extends Rule {
    public LocalVariablesStartWithLowerCase() {
        super(Constantes.LINT_REG_003, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(VariableDeclarator n, Void arg) {

                // check if the first character of the local variable is not lowercase
                if (!Character.isLowerCase(n.getNameAsString().charAt(0))) {
                    final Violation violation = new Violation();

                    // set filename, line number, and description
                    violation.setDescription("VariableName must start with lower case");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(n.getBegin().get().line);
                    // add the violation to the list of violations
                    addViolation(violation);
                }
                super.visit(n, arg);
            }
        }, null);
    }



    @Override
    public boolean isActive() {
        return true;
    }
}
