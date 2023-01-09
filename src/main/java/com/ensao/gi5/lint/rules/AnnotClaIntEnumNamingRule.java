package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

import java.lang.instrument.ClassDefinition;
import java.util.List;

public class AnnotClaIntEnumNamingRule extends Rule {


    public AnnotClaIntEnumNamingRule() {
        super(Constantes.LINT_REG_002, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<TypeDeclaration> typeDeclarations = compilationUnit.getTypeDeclaration();
        for (TypeDeclaration td : typeDeclarations) {
            if (td instanceof EnumDeclaration) {
                this.typeDeclarationCheck(td, "enumeration", compilationUnit);
            } else if (td instanceof AnnotationDeclaration) {
                this.typeDeclarationCheck(td, "annotation", compilationUnit);
            } else if (td instanceof ClassOrInterfaceDeclaration) {
                td = (ClassOrInterfaceDeclaration) td;
                if (((ClassOrInterfaceDeclaration) td).isInterface()) {
                    this.typeDeclarationCheck(td, "interface", compilationUnit);

                } else {
                    this.typeDeclarationCheck(td, "classe", compilationUnit);
                }

        }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }

    public void typeDeclarationCheck(TypeDeclaration typeDeclaration, String category, CompilationUnitWrapper compilationUnit) {
        if (typeDeclaration.getNameAsString().contains("_")) {
            Violation violation = new Violation();
            violation.setDescription("Le nom de" + category + " '" + typeDeclaration.getNameAsString() + "' ne doit pas contenir de '_'");
            violation.setFileName(compilationUnit.getFileName());
            violation.setLine(typeDeclaration.getRange().get().begin.line);
            addViolation(violation);
        } else if (Character.isLowerCase(typeDeclaration.getNameAsString().charAt(0))) {
            Violation violation = new Violation();
            violation.setDescription("Le nom de " + category + " '" + typeDeclaration.getNameAsString() + "' doit commencer avec majuscule");
            violation.setFileName(compilationUnit.getFileName());
            violation.setLine(typeDeclaration.getRange().get().begin.line);
            addViolation(violation);
        }
    }

}
