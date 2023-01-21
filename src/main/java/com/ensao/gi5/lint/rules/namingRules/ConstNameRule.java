package com.ensao.gi5.lint.rules.namingRules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.Level;
import com.ensao.gi5.lint.visitor.NameVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.NameWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ConstNameRule extends GeneralNamingRule{
    public ConstNameRule() {
        super(Constantes.LINT_REG_005,Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Map<String, List<NameWrapper>> cons = new HashMap<>();
        compilationUnit.accept(new NameVisitor(),cons);
        Predicate<NameWrapper> predicate = v -> !Pattern.compile("[A-Z0-9_]+").matcher(v.getSimpleName().asString()).matches();
        checkNamingRules(cons,predicate,Constantes.LINT_REG_005,compilationUnit);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
