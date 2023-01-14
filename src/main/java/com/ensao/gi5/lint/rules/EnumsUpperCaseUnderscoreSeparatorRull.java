package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.EnumDeclaration;

import java.util.List;

public class EnumsUpperCaseUnderscoreSeparatorRull extends Rule {

    public EnumsUpperCaseUnderscoreSeparatorRull() {
        super(Constantes.LINT_REG_007, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        // obtenir toutes les déclarations enums
        List<EnumDeclaration> enumDeclarations = compilationUnit.getEnums();
        // iterrer chaque enum
        for (EnumDeclaration enumDeclaration : enumDeclarations) {
            // vérifier si les caractères enum sont tous en minuscules et utiliser le trait de soulignement comme séparateur
            if (!enumDeclaration.getNameAsString().matches("^[A-Z_]+$")) {
                Violation violation = new Violation();
                // set filename, line number, and description
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(enumDeclaration.getBegin().get().line);
                violation.setDescription(enumDeclaration.getNameAsString() + "doit avoir tous les caractères en majuscule lors de l'utilisation du séparateur de soulignement");
                //  Ajouter l'objet Violation à la liste des violations
                addViolation(violation);
            }

        }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
