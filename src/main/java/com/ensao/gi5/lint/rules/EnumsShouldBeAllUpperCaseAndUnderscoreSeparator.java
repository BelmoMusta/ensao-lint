package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.EnumDeclaration;

import java.util.List;

public class EnumsShouldBeAllUpperCaseAndUnderscoreSeparator extends Rule {

    public EnumsShouldBeAllUpperCaseAndUnderscoreSeparator() {
        super(Constantes.LINT_REG_007, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        // get all enums declarations
        List<EnumDeclaration> enumDeclarations = compilationUnit.getEnums();

        // iterate through each enum
        for (EnumDeclaration enumDeclaration : enumDeclarations) {

            // check if enum characters are all lower case and use underscore as separator
            if (!enumDeclaration.getNameAsString().matches("^[A-Z_]+$")) {

                Violation violation = new Violation();

                // set filename, line number, and description
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(enumDeclaration.getBegin().get().line);
                violation.setDescription(enumDeclaration.getNameAsString() + " must have all characters to uppercase while using underscore separator");

                // add the violation to the list of violations
                addViolation(violation);
            }

        }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
