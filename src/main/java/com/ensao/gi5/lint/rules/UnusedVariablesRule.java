package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.util.ViolationFactory;
import com.ensao.gi5.lint.visitor.UnusedVariablesVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.VariableWrapper;

import java.util.ArrayList;
import java.util.List;

public class UnusedVariablesRule extends Rule{

    public UnusedVariablesRule() {
        super(Constantes.LINT_REG_016, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper cu) {
        List<VariableWrapper> declarations = new ArrayList<>();
        cu.accept(new UnusedVariablesVisitor(), declarations);
        declarations.forEach(d -> {
            if(d.getUsageCount()==1){
                addViolation(ViolationFactory.create(Constantes.LINT_REG_016, cu, d.getLine(), d.getName(), d.getClassName(), d.getMethod()));
            }
        });
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
