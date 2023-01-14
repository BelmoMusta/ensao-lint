package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.LambdaVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import java.util.ArrayList;
import java.util.List;
/**
 * This code defines a class called "LambdaRule" that extends a parent class called "Rule".
 * The "apply" method takes a "CompilationUnitWrapper" object and uses it to check for inituitive lambdas using the "LambdaVisitor" visitor class.
 * The "LambdaVisitor" check for lambda expression that has only one statement and it's a method call ,
 * and adds a new RuleWrapper object to a list with the name of the lambda expr and the line number where it is located in the source code.
 * Then the apply method check the the list return by the visitor if is not empty ,a new violation will be added.
 * The isActive method is overriding the parent class method, returns always true, it means that this rule is always active.
 * */
public class LambdaRule extends Rule {
    public LambdaRule() {
        super(Constantes.LINT_REG_010, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
            List<RuleWrapper> intuitiveLambdas = new ArrayList<>();
            compilationUnit.accept(new LambdaVisitor(intuitiveLambdas), null);
            if (intuitiveLambdas.size() != 0) {
                for (RuleWrapper lambda : intuitiveLambdas) {
                    Violation violation = ViolationMaker.makeViolation(
                            compilationUnit.getFileName(),
                            "Intuitive lambda expression found, consider using method reference instead.",
                            lambda.getLine());
                    addViolation(violation);
                }
            }
    }
    @Override
    public boolean isActive() {
        return true;
    }
}