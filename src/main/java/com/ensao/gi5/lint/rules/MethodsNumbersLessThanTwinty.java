package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;


public class MethodsNumbersLessThanTwinty extends Rule{
    public MethodsNumbersLessThanTwinty() {
        super(Constantes.LINT_REG_011, Level.HIGHEST);
    }

    public void apply(CompilationUnitWrapper compilationUnit) {
        for (TypeDeclaration<?> type : compilationUnit.getTypes()) {
            int methodCount = 0;
            for (BodyDeclaration<?> member : type.getMembers()) {
                if (member instanceof MethodDeclaration) {
                    methodCount++;
                }
            }
            if (methodCount > 20) {
                Violation vio = new Violation();
                vio.setDescription("Class " + type.getNameAsString() + " has more than 20 methods declared.");
                vio.setFileName(compilationUnit.getFileName());
                vio.setLine(type.getBegin().get().line);
                addViolation(vio);
            }
        }

        for (MethodDeclaration method : compilationUnit.getMethods()) {
            if (method.getBody().isPresent()) {
                if (method.getBody().get().getStatements().isEmpty()) {
                    Violation vio = new Violation();
                    vio.setDescription("The Method " + method.getNameAsString() + " is missing curly braces.");
                    vio.setFileName(compilationUnit.getFileName());
                    vio.setLine(method.getBegin().get().line);
                    addViolation(vio);
                }
            }
        }
    }




    @Override
    public boolean isActive() {
        return true;
    }
}
