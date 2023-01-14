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
/**
 * This is a Java class called NumberOfParametersRule that extends the Rule class.
 * The "apply" method takes a "CompilationUnitWrapper" object and uses it to check for number of parameters in a method
 * or a constructor using the "ParameterVisitor" visitor class.
 * The "ClassMethodsVisitor" check the number of parameters in a method or a constructor, if there are more than 2 parameters
 * it adds a new RuleWrapper object to a list with the number of parameters and the line number where it is located in the source code.
 * Then the "apply" methos check the list returned by the visitor if is not empty, new violation will be added
 * The isActive method is overriding the parent class method, returns always true, it means that this rule is always active.
 * */
public class NumberOfParametersRule extends Rule {
    public NumberOfParametersRule() {
        super(Constantes.LINT_REG_012, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try {
            CompilationUnit unit = compilationUnit.getParser();
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

