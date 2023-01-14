package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.NamingVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.NamingWrapper;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * The NamingRule class is a Java class that extends the Rule abstract class.
 * It is used to check the naming convention of class, methods,
 * variables and other elements within a compilation unit.
 *
 * The apply method is an implementation of the abstract method in the Rule class.
 * It uses the NamingVisitor class to traverse the compilation unit and collect
 * all elements in a set. It then iterates through the set of elements and checks
 * if the name of the element starts with a capital letter and does not contain an underscore.
 * If an element does not follow this naming convention, a violation is created using the
 * ViolationFactory class and added to the violations list using the addViolation method.
 *
 * This class is a rule that checks the naming convention of class, methods, variables and
 * other elements within a compilation unit. It will return a violation if the elements
 * name does not start with a Capital letter or contains an underscore.
 *
 * **/
public class NamingRule extends Rule{
    public NamingRule() {
        super(Constantes.LINT_REG_002, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        final Set<NamingWrapper> namingWrapperSet = new LinkedHashSet<>();
        compilationUnit.accept(new NamingVisitor(),namingWrapperSet);

        for (NamingWrapper namingWrapper: namingWrapperSet) {
            String name = namingWrapper.getName();
            if(!Character.isUpperCase(name.charAt(0)) || name.contains("_")){
                final Violation violation = ViolationFactory.ViolationFactory(
                        compilationUnit.getFileName(),
                        namingWrapper.getType()+"'"+namingWrapper.getName()+"' Does not start with Capital letter or it contains '_'",
                        namingWrapper.getLine());
                addViolation(violation);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
