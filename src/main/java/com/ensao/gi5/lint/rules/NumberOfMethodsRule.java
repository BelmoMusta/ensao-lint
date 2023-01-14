package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.visitor.NumberOfMethodsVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * The NumberOfMethodsRule class is a Java class that extends the Rule abstract class.
 * It is used to check the number of methods within a class of a compilation unit.
 *
 * The apply method is an implementation of the abstract method in the Rule class.
 * It uses the NumberOfMethodsVisitor class to traverse the compilation unit and
 * collect all method declarations in a set. Then, it checks the number of methods
 * in the set. If the number of methods is greater than 20, a violation is created
 * using the ViolationFactory class and added to the violations list using the addViolation method.
 *
 * This class is a rule that checks the number of methods within a class of a compilation unit.
 * It will return a violation if the number of methods is greater than 20.
 * **/
public class NumberOfMethodsRule extends Rule{
    public NumberOfMethodsRule() {
        super(Constantes.LINT_REG_011, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        final Set<MethodDeclaration> allMethods = new LinkedHashSet<>();
        compilationUnit.accept(new NumberOfMethodsVisitor(),allMethods);

            //allMethods.stream().findFirst().get() this line help us to get the first method
            // and after that we use this method to get the className
            if(allMethods.size()>20){
                final Violation violation = ViolationFactory.ViolationFactory(
                        compilationUnit.getFileName(),
                        "Number of methods in the class '"+ Utils.findClassName(allMethods.stream().findFirst().get()) +"'is more than 20 !",
                        1);
                addViolation(violation);
            }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
