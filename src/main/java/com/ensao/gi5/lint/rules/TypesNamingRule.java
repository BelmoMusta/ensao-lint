package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.TypeDeclaration;
import java.util.List;

public class TypesNamingRule extends Rule{

    protected TypesNamingRule() {
        super(Constantes.LINT_REG_002, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        // Obtenir tous les types déclarés dans le fichier
        final List<TypeDeclaration<?>> types = compilationUnit.getTypes();

        types.stream()
                .filter(type -> !Character.isUpperCase(type.getNameAsString().charAt(0)) || type.getNameAsString().contains("_"))
                .forEach(type -> {
                    String message = "";
                    if (!Character.isUpperCase(type.getNameAsString().charAt(0))) {
                        message += "NAme of Type " + type.getNameAsString() + "  must start with uppercase letter.";
                    }
                    if (type.getNameAsString().contains("_")) {
                        message += " Le nom du type " + type.getNameAsString() + " ne doit pas contenir de tirets bas.";
                    }
                    final Violation violation = new Violation();
                    violation.setDescription(message);
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(type.getName().getBegin().get().line);
                    addViolation(violation);
                });



    }

    @Override
    public boolean isActive() {
        return true;
    }
}

