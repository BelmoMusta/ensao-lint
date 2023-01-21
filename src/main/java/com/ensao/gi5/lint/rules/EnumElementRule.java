package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.EnumDeclaration;

import java.util.List;

public class EnumElementRule extends Rule{
    public EnumElementRule() {
        super(Constantes.LINT_REG_007, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        // Get a list of all enum declarations in the file
        List<EnumDeclaration> enums = compilationUnit.getCompilationUnit().findAll(EnumDeclaration.class);
        // Use a stream to check the names of the enum elements
        enums.stream()
                .forEach(enumDeclaration -> {
                    // Iterate over the elements of the enum
                    for (int i = 0; i < enumDeclaration.getEntries().size(); i++) {
                        String name = enumDeclaration.getEntries().get(i).getNameAsString();
                        // Check if the name is written in uppercase
                        if (!name.equals(name.toUpperCase())) {
                            final Violation violation = new Violation();
                            violation.setDescription("Enum element name must be written in uppercase: " + name);
                            violation.setFileName(compilationUnit.getFileName());
                            addViolation(violation);
                        }
                        // Check if the name contains underscores
                        if (!name.contains("_")) {
                            final Violation violation = new Violation();
                            violation.setDescription("Enum element name must contain underscores: " + name);
                            violation.setFileName(compilationUnit.getFileName());
                            addViolation(violation);
                        }
                    }
                });
    }


@Override
    public boolean isActive() {
        return true ;
    }
}
