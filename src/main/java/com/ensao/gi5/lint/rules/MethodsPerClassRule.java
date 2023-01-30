package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**

 This class extends the {@link Rule} class and checks for violations of the rule that states a class should

 not have more than 20 methods. The rule is identified by the constant {@link Constantes#LINT_REG_011}

 and has the highest level of severity.

 The {@link #apply(CompilationUnitWrapper)} method uses the visitor pattern to traverse the abstract syntax tree

 of the compilation unit and checks for classes with more than 20 methods. If a violation is found,

 an instance of {@link Violation} is created with the appropriate description, file name, and line number and is added

 to the list of violations.

 The {@link #isActive()} method is overridden to always return false, indicating that this rule is inactive by default.
 */
public class MethodsPerClassRule extends Rule{
    public MethodsPerClassRule() {
        super(Constantes.LINT_REG_011, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                long methodCount = n.getMethods().stream().filter(m -> !m.isStatic()).count();
                if (methodCount > 20) {
                    final Violation violation = new Violation();
                    violation.setDescription("Class should not have more than 20 methods");
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
        return false;
    }
}
