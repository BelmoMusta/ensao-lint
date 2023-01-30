package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**

 This class extends the {@link Rule} class and checks for violations of the rule that states a method body should not exceed

 30 lines. The rule is identified by the constant {@link Constantes#LINT_REG_008} and has the highest level of severity.

 The {@link #apply(CompilationUnitWrapper)} method uses the visitor pattern to traverse the abstract syntax tree

 of the compilation unit and checks for method declarations with a body that exceeds 30 lines. If a violation is found,

 an instance of {@link Violation} is created with the appropriate description, file name, and line number and is added

 to the list of violations.

 The {@link #isActive()} method is overridden to always return true, indicating that this rule is always active.
 */
public class MethodBodyRule extends Rule{
    public MethodBodyRule() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(MethodDeclaration n, Void arg) {
                if (n.getBody().isPresent()) {
                    int lines = n.getBody().get().toString().split("\r\n|\r|\n").length;
                    if (lines > 30) {
                        final Violation violation = new Violation();
                        violation.setDescription("Method body should not exceed 30 lines");
                        violation.setFileName(compilationUnit.getFileName());
                        violation.setLine(n.getBegin().get().line);
                        addViolation(violation);
                    }
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
