package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;

import java.io.File;
import java.util.List;

public class NumberOfParametersRule extends Rule {
    public NumberOfParametersRule() {
        super(Constantes.LINT_REG_012, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try {
            CompilationUnit unit = StaticJavaParser.parse(new File(compilationUnit.getFileName()));
            List<MethodDeclaration> methods = unit.findAll(MethodDeclaration.class);
            for (MethodDeclaration method : methods) {
                int numParams = method.getParameters().size();
                if (numParams > 2) {
                    final Violation violation = new Violation();
                    violation.setDescription("Number of parameters exceeds 2");
                    violation.setFileName(compilationUnit.getFileName());
                    int line = method.getBegin().get().line;
                    violation.setLine(line);
                    addViolation(violation);
                }
            }
            List<ConstructorDeclaration> constructors = unit.findAll(ConstructorDeclaration.class);
            for (ConstructorDeclaration constructor : constructors) {
                int numParams = constructor.getParameters().size();
                if (numParams > 2) {
                    final Violation violation = new Violation();
                    violation.setDescription("Number of parameters exceeds 2 in constructor");
                    violation.setFileName(compilationUnit.getFileName());
                    int line = constructor.getBegin().get().line;
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

