package com.ensao.gi5.lint.rules;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;

import java.util.List;

public class ClassFieldVisibilityRule extends Rule {
    public ClassFieldVisibilityRule() {
        super(Constantes.LINT_REG_013, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<FieldDeclaration> fields = compilationUnit.getCompilationUnit().findAll(FieldDeclaration.class);
        fields
                .forEach(fieldDeclaration -> {
                    if (!fieldDeclaration.isPrivate() && !fieldDeclaration.isProtected() && !fieldDeclaration.isPublic()) {
                        final Violation violation = new Violation();
                        violation.setDescription("Le champ de classe doit avoir une visibilité déclarée: " + fieldDeclaration.getVariable(0).getNameAsString());
                        violation.setFileName(compilationUnit.getFileName());
                        addViolation(violation);
                    }
                });
    }

    @Override
    public boolean isActive() {
        return true;
    }


}

