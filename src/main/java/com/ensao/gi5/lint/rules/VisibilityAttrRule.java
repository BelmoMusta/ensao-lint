package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.FieldWrapper;

import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.stream.Collectors;


public class VisibilityAttrRule extends Rule{
    public VisibilityAttrRule() {
        super(Constantes.LINT_REG_013, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        // Récupération de tous les attributs de classe
        final Set<FieldWrapper> declaredFields = compilationUnit.getFields()
                .stream()
                .map(fieldDeclaration -> new FieldWrapper(fieldDeclaration.getVariable(0).getName(), fieldDeclaration.getModifiers()))
                .collect(Collectors.toSet());

        // Parcours de chaque attribut
        for (FieldWrapper field : declaredFields) {
            // Vérification si l'attribut n'a pas de visibilité déclarée
            if (!field.getModifiers().contains(Modifier.PUBLIC) && !field.getModifiers().contains(Modifier.PRIVATE) && !field.getModifiers().contains(Modifier.PROTECTED)) {
                // Création d'un objet Violation
                final Violation violation = new Violation();
                violation.setDescription("L'attribut '" + field.getName() + "' n'a pas de visibilité déclarée");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(field.getLine());

                // Ajout de l'objet Violation à la liste des violations
                addViolation(violation);
            }
        }
    }


    @Override
    public boolean isActive() {
        return true;
    }
}
