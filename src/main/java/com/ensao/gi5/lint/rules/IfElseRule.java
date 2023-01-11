package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.IfElseVisitor;
import com.ensao.gi5.lint.visitor.UnusedPrivateMethodsVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.util.Hashtable;
import java.util.List;

public class IfElseRule extends Rule{
    public IfElseRule() {
        super(Constantes.LINT_REG_018, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try {
            CompilationUnit unit = StaticJavaParser.parse(new File(compilationUnit.getFileName()));
            Hashtable<String,Integer> missingIfBrackets = new Hashtable<>();
            Hashtable<String,Integer> missingElseBrackets = new Hashtable<>();
            unit.accept(new IfElseVisitor(missingIfBrackets,missingElseBrackets), null);
            if(missingIfBrackets.size()!=0 || missingElseBrackets.size()!=0){
                for (String miss : missingIfBrackets.keySet()) {
                    final Violation violation = new Violation();
                    violation.setDescription("Missing Brackets on If statements");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(missingIfBrackets.get(miss));
                    addViolation(violation);
                }
                for (String miss : missingElseBrackets.keySet()) {
                    final Violation violation = new Violation();
                    violation.setDescription("Missing Brackets on Else statements");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(missingElseBrackets.get(miss));
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
