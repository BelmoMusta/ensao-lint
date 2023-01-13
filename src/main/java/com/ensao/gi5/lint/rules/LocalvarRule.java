package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.LocalvarVisitors;
import com.ensao.gi5.lint.visitor.NominationVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.NominationWrapper;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LocalvarRule extends Rule{
    public LocalvarRule() {
        super(Constantes.LINT_REG_003, Level.HIGHEST);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        final Set<String> localvariables = new LinkedHashSet<>();
        compilationUnit.accept(new LocalvarVisitors(), localvariables);


        Set<String> localvar=localvariables.stream().filter(p->Character.isUpperCase(p.charAt(1))).collect(Collectors.toSet());

    if(!localvar.isEmpty()){
        for (String lvar:localvar){
        final Violation violation = new Violation();
        violation.setDescription("local variable name violation '" + lvar + "'");
        violation.setFileName(compilationUnit.getFileName());
       // violation.setLine(n.getLine());
        addViolation(violation);
    }}

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
