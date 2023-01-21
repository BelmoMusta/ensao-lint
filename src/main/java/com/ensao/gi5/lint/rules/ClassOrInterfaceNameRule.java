package com.ensao.gi5.lint.rules;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

import java.util.List;


public class ClassOrInterfaceNameRule extends Rule{
    public ClassOrInterfaceNameRule() {
        super(Constantes.LINT_REG_002, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<TypeDeclaration<?>> types = compilationUnit.getCompilationUnit().getTypes();
        types.stream()
                .filter(ClassOrInterfaceDeclaration.class::isInstance)
                .map(ClassOrInterfaceDeclaration.class::cast)
                .forEach(classOrInterface -> {
                    String name = classOrInterface.getNameAsString();
                    int lineNumber = classOrInterface.getName().getBegin().get().line;
                    if (!Character.isUpperCase(name.charAt(0))) {
                        final Violation violation = new Violation();
                        violation.setDescription("Le nom de la classe ou de l'interface ou bien enum doit commencer par une majuscule: " + name);
                        violation.setFileName(compilationUnit.getFileName());
                        violation.setLine(lineNumber);
                        addViolation(violation);
                    }
                    if (name.contains("_")) {
                        final Violation violation = new Violation();
                        violation.setDescription("Le nom de la classe ou de l'interface ou enum ne doit pas contenir de traits de soulignement: " + name);
                        violation.setFileName(compilationUnit.getFileName());
                        violation.setLine(lineNumber);
                        addViolation(violation);
                    }
                });
    }

    @Override
    public boolean isActive() {
        return true;
    }
}

