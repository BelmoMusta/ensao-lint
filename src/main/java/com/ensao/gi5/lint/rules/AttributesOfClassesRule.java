package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

public class AttributesOfClassesRule extends Rule{

    public AttributesOfClassesRule () {super(Constantes.LINT_REG_004, Level.HIGH);}

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
//same as rule 5 but with different matching regex "[a-z]\w*"
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
