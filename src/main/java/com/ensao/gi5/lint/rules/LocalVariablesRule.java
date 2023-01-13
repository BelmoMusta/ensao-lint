package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**

 This class extends the {@link Rule} class and checks for violations of the rule that states local variables

 should start with a lowercase letter. The rule is identified by the constant {@link Constantes#LINT_REG_003}

 and has the highest level of severity.

 The {@link #apply(CompilationUnitWrapper)} method uses the visitor pattern to traverse the abstract syntax tree

 of the compilation unit and checks for local variable declarations that do not start with a lowercase letter. If a violation is found,

 an instance of {@link Violation} is created with the appropriate description, file name, and line number and is added

 to the list of violations.

 The {@link #isActive()} method is overridden to always return true, indicating that this rule is always active.
 */
public class LocalVariablesRule extends Rule {
    public LocalVariablesRule() {
        super(Constantes.LINT_REG_003, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(VariableDeclarator n, Void arg) {
                if (!Character.isLowerCase(n.getNameAsString().charAt(0))) {
                    final Violation violation = new Violation();
                    violation.setDescription("Local variable should start with a lowercase letter");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(n.getBegin().get().line);
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

