package com.ensao.gi5.lint.rules;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

public class MethodCountRule extends Rule {
    public MethodCountRule() {
        super(Constantes.LINT_REG_011, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.getCompilationUnit().findAll(ClassOrInterfaceDeclaration.class)
                .forEach(classOrInterface -> {
                    long methodCount = classOrInterface.getMethods().size();
                    if (methodCount > 20) {
                        final Violation violation = new Violation();
                        violation.setDescription("La classe ou l'interface avoir  des méthodes déclarées: " + methodCount);
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
