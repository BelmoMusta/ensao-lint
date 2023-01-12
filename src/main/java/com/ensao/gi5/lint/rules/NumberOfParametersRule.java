package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ParameterVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.MethodParameterWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.File;
import java.util.ArrayList;
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

            List<MethodParameterWrapper> methodParameterCounts = new ArrayList<>();
            new ParameterVisitor().visit(unit, methodParameterCounts);

            for (MethodParameterWrapper methodParameterCount : methodParameterCounts) {
                if(methodParameterCount.getParameterCount() > 2) {
                    final Violation violation = new Violation();
                    violation.setDescription("Number of parameters exceeds 2");
                    violation.setFileName(compilationUnit.getFileName());
                    int line = methodParameterCount.getLine();
                    violation.setLine(line);
                    addViolation(violation);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}

