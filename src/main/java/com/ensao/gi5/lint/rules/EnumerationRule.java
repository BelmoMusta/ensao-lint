package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.util.ViolationFactory;
import com.ensao.gi5.lint.visitor.EnumerationVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.EnumerationWrapper;

import java.util.ArrayList;
import java.util.List;

public class EnumerationRule extends Rule{

    //Le constructeur par défaut
    public EnumerationRule() {

        super(Constantes.LINT_REG_007, Level.LOW);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        List<EnumerationWrapper> enumerationWrapperList = new ArrayList<>();
        compilationUnit.accept(new EnumerationVisitor(), enumerationWrapperList);

        //L'implémentation de la règle LINT_REG_007
        enumerationWrapperList.forEach(en -> en.getElementList().forEach(e -> {

            if(!e.getName().matches("[A-Z_]+")){
                addViolation(ViolationFactory.createViolation(this.getId(), compilationUnit, e.getLine()));
            }
        }));
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
