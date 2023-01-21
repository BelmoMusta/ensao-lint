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

public class AttrClassNameRule extends GeneralNamingRule{
    public AttrClassNameRule() {
        super(Constantes.LINT_REG_004,Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Map<String, List<NameWrapper>> attr = new HashMap<>();
        compilationUnit.accept(new NameVisitor(),attr);
        Predicate<NameWrapper> predicate = v -> !Pattern.compile("^[a-z]\\w*").matcher(v.getSimpleName().asString()).matches();
        checkNamingRules(attr,predicate,Constantes.LINT_REG_004,compilationUnit);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
