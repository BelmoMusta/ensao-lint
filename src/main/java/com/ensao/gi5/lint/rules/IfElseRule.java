package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.IfElseVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * This code defines a class called "IfElseRule" that extends a parent class called "Rule".
 * The "apply" method is overridden from the parent class and it is used to check for missing brackets in if and else statements.
 * It uses the "IfElseVisitor" class to identify missing brackets and creates a "Violation" object for each missing bracket
 * it finds.
 * The "isActive" method returns "true" which means the rule is active and will be applied.
 * */
public class IfElseRule extends Rule{
    public IfElseRule() {
        super(Constantes.LINT_REG_018, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try {
            CompilationUnit unit = StaticJavaParser.parse(new File(compilationUnit.getFileName()));
            List<RuleWrapper> missingIfBrackets = new ArrayList<>();
            List<RuleWrapper> missingElseBrackets = new ArrayList<>();
            unit.accept(new IfElseVisitor(missingIfBrackets,missingElseBrackets), null);
            if(missingIfBrackets.size()!=0 || missingElseBrackets.size()!=0){
                for (RuleWrapper miss : missingIfBrackets) {
                        Violation violation = ViolationMaker.makeViolation(
                                compilationUnit.getFileName(),
                                "Missing Brackets on If statements",
                                miss.getLine());
                        addViolation(violation);
                }
                for (RuleWrapper miss : missingElseBrackets) {
                    Violation violation = ViolationMaker.makeViolation(
                            compilationUnit.getFileName(),
                            "Missing Brackets on Else statements",
                            miss.getLine());
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
