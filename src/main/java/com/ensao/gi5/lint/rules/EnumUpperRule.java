package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.visitor.EnumVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.EnumWrapper;

import java.util.ArrayList;
import java.util.List;


public class EnumUpperRule extends Rule {

    public EnumUpperRule() {
        super(Constantes.LINT_REG_007, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<EnumWrapper> enums = new ArrayList<>();
        compilationUnit.accept(new EnumVisitor(),enums);
        enums.forEach(enu->enu.getEnumElements().forEach(a->{
            if(!a.getName().matches("[A-Z_]+")){
                addViolation(violationBuilder.setDescription(Constantes.LINT_REG_007)
                        .setRuleId(Constantes.LINT_REG_007)
                        .setFileName(compilationUnit.getFileName())
                        .setLevel(Level.LOW)
                        .setLine(a.getLine()).build());
            }
        }));
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
