package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.ClassAttributsNameVisitor;
import com.ensao.gi5.lint.wrapper.ClassAttributsNameWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * @author Hmama Mohammed
 * @version 1.0.0
 *
 * The ClassAttributsNameRule class is a Java class that extends the Rule abstract class.
 * It is used to check the naming convention of class attributes within a compilation unit.
 * The apply method is an implementation of the abstract method in the Rule class.
 * It uses the ClassAttributsNameVisitor class to traverse the compilation unit and collect
 * all class attributes in a set. It then filters the set of attributes by checking
 * if the name of the attribute starts with a lowercase letter using regular expression.
 * If an attribute does not follow this naming convention, a violation is created using
 * the ViolationFactory class and added to the violations list using the addViolation method.
 *
 * This class is a rule that checks the naming convention of class attributes within a compilation unit.
 * It will return a violation if the class attribute's name does not start with a lowercase letter.
 * **/
public class ClassAttributsNameRule extends Rule{
    public ClassAttributsNameRule() {
        super(Constantes.LINT_REG_004, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<ClassAttributsNameWrapper> allClassAttributes = new LinkedHashSet<>();
        compilationUnit.accept(new ClassAttributsNameVisitor(), allClassAttributes);
        allClassAttributes.stream().filter(attribute->!attribute.getName().matches("^[a-z].*")).forEach(att->{
            final Violation violation = ViolationFactory.ViolationFactory(
                    compilationUnit.getFileName(),
                    "L'attribut '"+att.getName() +"' de la class '"+att.getClassName()+"' Does not start with lowercase !",
                    att.getLine());
            addViolation(violation);
        });

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
