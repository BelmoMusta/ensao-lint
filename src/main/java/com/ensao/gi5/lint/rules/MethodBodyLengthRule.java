package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.List;

public class MethodBodyLengthRule extends  Rule{
   public MethodBodyLengthRule() {
       super(Constantes.LINT_REG_008, Level.HIGHEST);

    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<MethodDeclaration> methods = compilationUnit.getCompilationUnit().findAll(MethodDeclaration.class);
        methods.stream()
                .filter(method -> method.getBody().isPresent())
                .forEach(method -> {
                    if (method.getBody().get().toString().split("\n").length > 30) {
                        final Violation violation = new Violation();
                        violation.setDescription("Le corps d'une méthode ne doit pas dépasser 30 lignes");
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
