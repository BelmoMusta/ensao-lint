package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.AnnotationDeclaration;

public class TypeJavaNaming extends Rule{
    public TypeJavaNaming() {
        super(Constantes.LINT_REG_002, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration n, Void arg) {
                String className = n.getNameAsString();
                if (!Character.isUpperCase(className.charAt(0)) || className.contains("_")) {
                    final Violation violation = new Violation();
                    violation.setDescription("Java types should start with an uppercase letter and should not have underscores");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(n.getBegin().get().line);
                    addViolation(violation);
                }
                super.visit(n, arg);
            }
            @Override
            public void visit(EnumDeclaration n, Void arg) {
                String enumName = n.getNameAsString();
                if (!Character.isUpperCase(enumName.charAt(0)) || enumName.contains("_")) {
                    final Violation violation = new Violation();
                    violation.setDescription("Java types should start with an uppercase letter and should not have underscores");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(n.getBegin().get().line);
                    addViolation(violation);
                }
                super.visit(n, arg);
            }
            @Override
            public void visit(AnnotationDeclaration n, Void arg) {
                String annotationName = n.getNameAsString();
                if (!Character.isUpperCase(annotationName.charAt(0)) || annotationName.contains("_")) {
                    final Violation violation = new Violation();
                    violation.setDescription("Java types should start with an uppercase letter and should not have underscores");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(n.getBegin().get().line);
                    addViolation(violation);
                }
                super.visit(n, arg);
            }
        }, null);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
