package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.File;
import java.util.List;

public class MethodLengthRule extends Rule {
    public MethodLengthRule() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try {
            CompilationUnit unit = StaticJavaParser.parse(new File(compilationUnit.getFileName()));
            List<MethodDeclaration> methods = unit.findAll(MethodDeclaration.class);
            for (MethodDeclaration method : methods) {
                int methodLength = method.getEnd().get().line - method.getBegin().get().line + 1;
                if (methodLength >= 30) {
                    final Violation violation = new Violation();
                    violation.setDescription("Method body exceed 30 lines ");
                    violation.setFileName(compilationUnit.getFileName());
                    int line = method.getBegin().get().line;
                    violation.setLine(line);
                    addViolation(violation);
                }
            }
        } catch (Exception e) {
            // Handle exception
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}

