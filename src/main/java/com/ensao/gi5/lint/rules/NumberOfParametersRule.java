package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.visitor.NumberOfMethodsVisitor;
import com.ensao.gi5.lint.visitor.NumberOfParametersVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * The NumberOfParametersRule class is a Java class that extends the Rule abstract class.
 * It is used to check the number of parameters within a method or constructor of a compilation unit.
 *
 * The "apply" method contains the main logic of the class. It creates two sets, one for "MethodDeclaration"
 * and one for "ConstructorDeclaration". It then uses two visitors, "NumberOfMethodsVisitor" and
 * "NumberOfParametersVisitor", to populate these sets with all the methods and constructors respectively,
 * in the provided "CompilationUnitWrapper" object.
 *
 * This rule check the number of parameters in all constructors and methods,
 * and if it's more than 2 it will raise a violation.
 * **/
public class NumberOfParametersRule extends Rule{
    public NumberOfParametersRule() {
        super(Constantes.LINT_REG_012, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        final Set<MethodDeclaration> allMethods = new LinkedHashSet<>();
        compilationUnit.accept(new NumberOfMethodsVisitor(),allMethods);
        final Set<ConstructorDeclaration> allConstructors = new LinkedHashSet<>();
        compilationUnit.accept(new NumberOfParametersVisitor(),allConstructors);

        for (MethodDeclaration mtd: allMethods) {
            if(mtd.getParameters().size()>2)
            {
                final Violation violation = ViolationFactory.ViolationFactory(
                        compilationUnit.getFileName(),
                        "Number of parameters in the method '"+mtd.getNameAsString()+"' of the class '"+ Utils.findClassName(mtd) +"'is more than 2 !",
                        mtd.getBegin().get().line);
                addViolation(violation);
            }
        }
        for (ConstructorDeclaration construct:allConstructors) {
            if(construct.getParameters().size()>2) {
                final Violation violation = ViolationFactory.ViolationFactory(
                        compilationUnit.getFileName(),
                        "Number of parameters in the constructor of the class  '"+ Utils.findClassName(construct) +"'is more than 2 !",
                        construct.getBegin().get().line);
                addViolation(violation);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
