package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 This class extends the {@link Rule} class and checks for violations of the rule that states that enumeration elements

 must be in uppercase and use _ as separator. The rule is identified by the constant {@link Constantes#LINT_REG_007}

 and has a low level of severity.

 The {@link #apply(CompilationUnitWrapper)} method uses the visitor pattern to traverse the abstract syntax tree

 of the compilation unit and checks for enumeration elements that are not in uppercase or use _ as separator. If a violation is found,

 an instance of {@link Violation} is created with the appropriate description, file name, and line number and is added

 to the list of violations.

 The {@link #isActive()} method is overridden to always return true, indicating that this rule is always active.
 */
public class EnumRule extends Rule{
    public EnumRule() {
        super(Constantes.LINT_REG_007, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(EnumDeclaration n, Void arg) {
                for (EnumConstantDeclaration enumConstant : n.getEntries()) {
                    String name = enumConstant.getNameAsString();
                    if (!name.equals(name.toUpperCase()) || name.contains("_")) {
                        final Violation violation = new Violation();
                        violation.setDescription("Enumeration elements must be in uppercase and use _ as separator");
                        violation.setFileName(compilationUnit.getFileName());
                        violation.setLine(enumConstant.getBegin().get().line);
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
