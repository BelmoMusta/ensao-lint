package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ConstVisitors;
import com.ensao.gi5.lint.visitor.MembersVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConstRule extends Rule{
    public ConstRule() {
        super(Constantes.LINT_REG_005, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        final Set<String> m = new LinkedHashSet<>();
        compilationUnit.accept(new ConstVisitors(), m);


for(String s:m) {

    if (!s.equals(s.toUpperCase())) {
        final Violation violation = new Violation();
        violation.setDescription("Const name violation ");
        violation.setFileName(compilationUnit.getFileName());
        //violation.setLine(n.getLine());
        addViolation(violation);
    }
}

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
