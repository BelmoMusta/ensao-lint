package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.LambdaVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.util.Hashtable;

public class LambdaRule extends Rule {
    public LambdaRule() {
        super(Constantes.LINT_REG_010, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try {
            CompilationUnit unit = StaticJavaParser.parse(new File(compilationUnit.getFileName()));
            Hashtable<String, Integer> intuitiveLambdas = new Hashtable<>();
            unit.accept(new LambdaVisitor(intuitiveLambdas), null);
            if (intuitiveLambdas.size() != 0) {
                for (String lambda : intuitiveLambdas.keySet()) {
                    final Violation violation = new Violation();
                    violation.setDescription("Intuitive lambda expression found, consider using method reference instead.");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(intuitiveLambdas.get(lambda));
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