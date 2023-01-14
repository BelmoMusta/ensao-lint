package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

public class BooleanExpressionRule extends  Rule{
    public BooleanExpressionRule() {
        super(Constantes.LINT_REG_006, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
