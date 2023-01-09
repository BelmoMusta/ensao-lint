package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.util.ViolationFactory;
import com.ensao.gi5.lint.visitor.EnumVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.EnumWrapper.EnumWrapper;

import java.util.ArrayList;
import java.util.List;

public class EnumRule extends Rule{

    public EnumRule() {
        super(Constantes.LINT_REG_007, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper cu) {
        List<EnumWrapper> enums = new ArrayList<>();
        cu.accept(new EnumVisitor(), enums);

        enums.forEach(en -> en.getElements().forEach(e ->{
            if(!e.getName().matches("[A-Z_]+")){
                addViolation(ViolationFactory.create(this.id, cu, e.getLine()));
            }
        }));
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
