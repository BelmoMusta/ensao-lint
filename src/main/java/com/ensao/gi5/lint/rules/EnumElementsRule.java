package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.EnumElementVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.EnumElementWrapper;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * @author Hmama Mohammed
 * @version 1.0.0
 *
 *The EnumElementsRule class is a Java class that extends the Rule abstract class.
 * It is used to check the naming convention of enum elements within a compilation unit.
 *
 *
 * The apply method is an implementation of the abstract method in the Rule class.
 * It uses the EnumElementVisitor class to traverse the compilation unit and collect
 * all enum elements in a set. It then filters the set of elements by checking
 * if the name of the element follows the naming convention using regular expression
 * which is uppercase letters, digits and underscore. If an element does not follow this
 * naming convention, a violation is created using the ViolationFactory class and added to
 * the violations list using the addViolation method.
 *
 * This class is a rule that checks the naming convention of enum elements within
 * a compilation unit. It will return a violation if the enum element's name is
 * not all uppercase letters, digits, and underscores.
 *
 * **/
public class EnumElementsRule extends Rule{
    public EnumElementsRule() {
        super(Constantes.LINT_REG_007, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        Set<EnumElementWrapper> allEnumConstants = new LinkedHashSet<>();
        compilationUnit.accept(new EnumElementVisitor(), allEnumConstants);
        allEnumConstants.stream().filter(enumm->!enumm.getName().matches("^[A-Z0-9_]*")).forEach(enumm->{
            final Violation violation = ViolationFactory.ViolationFactory(
                    compilationUnit.getFileName(),
                    "La constante '"+enumm.getName() +"' de l'Enumeration '"+enumm.getClassName()+"' it's not all Uppercase !",
                    enumm.getLine());
            addViolation(violation);
        });


    }

    @Override
    public boolean isActive() {
        return true;
    }
}
