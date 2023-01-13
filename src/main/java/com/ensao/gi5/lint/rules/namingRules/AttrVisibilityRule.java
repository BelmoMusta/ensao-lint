package com.ensao.gi5.lint.rules.namingRules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.Level;
import com.ensao.gi5.lint.visitor.NameVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.NameWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttrVisibilityRule extends GeneralNamingRule{

    public AttrVisibilityRule() {
        super(Constantes.LINT_REG_013,Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Map<String, List<NameWrapper>> attr = new HashMap<>();
        compilationUnit.accept(new NameVisitor(),attr);
        checkNamingRules(attr,a -> a.getAccessSpec().isEmpty(),Constantes.LINT_REG_013,compilationUnit);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
