package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

import java.util.List;

public class ClassAttributesStartWithLowerCase extends Rule{
    public ClassAttributesStartWithLowerCase() {
        super(Constantes.LINT_REG_004, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        // get all field declarations
        List<FieldDeclaration> fieldDeclarations = compilationUnit.getFields();

        // iterate through each field
        for (FieldDeclaration fieldDeclaration : fieldDeclarations) {

            List<VariableDeclarator> variableDeclarators = fieldDeclaration.getVariables();

            // iterate through each variable declarator
            for (VariableDeclarator variableDeclarator : variableDeclarators) {

                // check if the first character of the attribute is not lowercase
                if (!Character.isLowerCase(variableDeclarator.getNameAsString().charAt(0)) && !fieldDeclaration.isFinal()) {

                    Violation violation = new Violation();

                    // set filename, line number, and description
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(variableDeclarator.getBegin().get().line);
                    violation.setDescription(variableDeclarator.getNameAsString() + " must start with lower case");

                    // add the violation to the list of violations
                    addViolation(violation);
                }
            }
        }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
