package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class LocalVariablesStartWithLowerCase extends Rule {
    public LocalVariablesStartWithLowerCase() {
        super(Constantes.LINT_REG_003, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        // get all methods declarations
        List<MethodDeclaration> methodDeclarations = compilationUnit.getMethods();

        // iterate through each method
        for (MethodDeclaration methodDeclaration : methodDeclarations) {

            List<VariableDeclarator> variableDeclarators = methodDeclaration.getType().findAll(VariableDeclarator.class);

            // iterate through each variable declarator
            for (VariableDeclarator variableDeclarator : variableDeclarators) {

                // check if the first character of the local variable is not lowercase
                if (!Character.isLowerCase(variableDeclarator.getNameAsString().charAt(0)) && !methodDeclaration.isFinal()) {

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
