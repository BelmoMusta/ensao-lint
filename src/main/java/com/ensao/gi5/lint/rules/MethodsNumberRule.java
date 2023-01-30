package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;


public class MethodsNumberRule extends Rule{
    public MethodsNumberRule() {
        super(Constantes.LINT_REG_011, Level.HIGHEST);
    }

    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.getTypes().forEach(type -> {
            long methodCount = type.getMembers().stream()
                    .filter(member -> member instanceof MethodDeclaration)
                    .count();
            if (methodCount > 20) {
                Violation violance = new Violation();
                violance.setDescription("Class " + type.getNameAsString() + " has more than 20 methods declared.");
                violance.setFileName(compilationUnit.getFileName());
                violance.setLine(type.getBegin().get().line);
                addViolation(violance);
            }
        });
    }
    @Override
    public boolean isActive() {
        return true;
    }
}
