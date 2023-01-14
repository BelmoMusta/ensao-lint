package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.List;

public class ParameterMethodConstructorMax2Rule extends Rule{

    public ParameterMethodConstructorMax2Rule() {
        super(Constantes.LINT_REG_012, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        // Récupérer les déclarations de la méthode
        final List<MethodDeclaration> list = compilationUnit.getMethods();
        // Itérer chaque déclaration de la méthode
        for (MethodDeclaration method : list) {
            // Vérifier si la méthode a plus de 2 paramètres
            if (method.getParameters().size() > 2) {
                final Violation violation = new Violation();
                violation.setDescription("La methode contient plus que 2 parametres");
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(method.getBegin().get().line);
                // Ajouter l'objet Violation à la liste des violations
                addViolation(violation);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
