package com.ensao.gi5.lint.rules;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.List;

public class MethodParametersRule extends  Rule{
    public MethodParametersRule() {
        super(Constantes.LINT_REG_012, Level.HIGHEST);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<MethodDeclaration> methods = compilationUnit.getCompilationUnit().findAll(MethodDeclaration.class);
        methods
                .forEach(method -> {
                    int numberOfParameters = method.getParameters().size();
                    if (numberOfParameters > 2) {
                        final Violation violation = new Violation();
                        violation.setDescription("Le nombre de paramètres d'une méthode/constructeur ne doit dépasser 2 " + method.getNameAsString());
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
