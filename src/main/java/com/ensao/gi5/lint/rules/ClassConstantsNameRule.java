package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.ClassConstantsNameVisitor;
import com.ensao.gi5.lint.wrapper.ClassAttributsNameWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * The ClassConstantsNameRule class is a Java class that extends the Rule abstract class.
 * It is used to check the naming convention of class constants within a compilation unit.
 * The apply method is an implementation of the abstract method in the Rule class.
 * It uses the ClassConstantsNameVisitor class to traverse the compilation unit and collect
 * all class constants in a set. It then filters the set of constants by checking
 * if the name of the constant follows the naming convention using regular expression,
 * which is uppercase letters, digits and underscore.
 * If a constant does not follow this naming convention, a violation is created using the
 * ViolationFactory class and added to the violations list using the addViolation method.
 *
 * **/
public class ClassConstantsNameRule extends Rule{
    public ClassConstantsNameRule() {
        super(Constantes.LINT_REG_005, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<ClassAttributsNameWrapper> allClassConstants = new LinkedHashSet<>();
        compilationUnit.accept(new ClassConstantsNameVisitor(), allClassConstants);
        allClassConstants.stream().filter(attribute->!attribute.getName().matches("^[A-Z0-9_]*")).forEach(att->{
            final Violation violation = ViolationFactory.ViolationFactory(
                    compilationUnit.getFileName(),
                    "La constante '"+att.getName() +"' de la class '"+att.getClassName()+"' it's not all Uppercase !",
                    att.getLine());
            addViolation(violation);
        });
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
