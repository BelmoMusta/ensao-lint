package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.ParameterVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleCountWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

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
            List<RuleCountWrapper> methodParameterCounts = new ArrayList<>();
            new ParameterVisitor().visit(unit, methodParameterCounts);

            for (RuleCountWrapper methodParameterCount : methodParameterCounts) {
                if(methodParameterCount.getCount() > 2) {


                    int line = methodParameterCount.getLine();
                    Violation violation = ViolationMaker.makeViolation(
                            compilationUnit.getFileName(),
                            "Number of parameters exceeds 2",
                            line);
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

