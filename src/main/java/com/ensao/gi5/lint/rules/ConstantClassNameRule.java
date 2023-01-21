package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.FieldDeclaration;

public class ConstantClassNameRule extends Rule {
    public ConstantClassNameRule() {
        super(Constantes.LINT_REG_005, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
            compilationUnit.getCompilationUnit().findAll(FieldDeclaration.class).forEach(fieldDeclaration -> {
                if (fieldDeclaration.isFinal() && fieldDeclaration.isStatic()) {
                    String name = fieldDeclaration.getVariables().get(0).getNameAsString();
                    int lineNumber = fieldDeclaration.getVariables().get(0).getBegin().get().line;
                    if (!name.equals(name.toUpperCase())) {
                        final Violation violation = new Violation();
                        violation.setDescription("Les constantes d'une classe sont écrites en majuscule: " + name);
                        violation.setFileName(compilationUnit.getFileName());
                        violation.setLine(lineNumber);
                        addViolation(violation);
                    }
                    if (!name.contains("_")) {
                        final Violation violation = new Violation();
                        violation.setDescription("Les constantes d'une classe sont écrites avec des _ comme séparateur: " + name);
                        violation.setFileName(compilationUnit.getFileName());
                        violation.setLine(lineNumber);
                        addViolation(violation);
                    }
                }
            });

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
