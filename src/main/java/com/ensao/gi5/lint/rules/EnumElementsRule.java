package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.ClassConstantsNameVisitor;
import com.ensao.gi5.lint.visitor.EnumElementVisitor;
import com.ensao.gi5.lint.wrapper.ClassAttributsNameWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.EnumElementWrapper;

import java.util.LinkedHashSet;
import java.util.Set;

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
