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

public class VarLocNameRule extends GeneralNamingRule {

    public VarLocNameRule(){super(Constantes.LINT_REG_003, Level.HIGH);}
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Map<String, List<NameWrapper>> var = new HashMap<>();
        compilationUnit.accept(new NameVisitor(),var);
        Predicate<NameWrapper> predicate = v -> !Pattern.compile("^[a-z]\\w*").matcher(v.getSimpleName().asString()).matches();
        checkNamingRules(var,predicate,Constantes.LINT_REG_003,compilationUnit);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
