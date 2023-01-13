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

public class NamesRule extends GeneralNamingRule {



    public NamesRule() {
        super(Constantes.LINT_REG_002, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Map<String, List<NameWrapper>> var = new HashMap<>();
        compilationUnit.accept(new NameVisitor(),var);
        Predicate<NameWrapper> predicate =v -> !Pattern.compile("^[A-Z][A-Za-z0-9]*").matcher(v.getSimpleName().asString()).matches();
        checkNamingRules(var,predicate,Constantes.LINT_REG_002,compilationUnit);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
