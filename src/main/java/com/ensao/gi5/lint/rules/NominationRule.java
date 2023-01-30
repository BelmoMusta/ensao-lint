package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.NominationVisitors;
import com.ensao.gi5.lint.visitor.UnusedImportsVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.ImportWrapper;
import com.ensao.gi5.lint.wrapper.NominationWrapper;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NominationRule extends Rule{

    public NominationRule() {
        super(Constantes.LINT_REG_002, Level.HIGHEST);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        final Set<NominationWrapper> nominations = new LinkedHashSet<>();
        compilationUnit.accept(new NominationVisitors(), nominations);
        for (NominationWrapper m:nominations){System.out.println(m);}
        Predicate<Object> p1= p->(Character.isLowerCase(p.toString().charAt(0)));
        Predicate<Object> p2= p->(p.toString().contains("_"));
        Predicate<Object> p3= p1.or(p2);


        Set<NominationWrapper> answer=nominations.stream().filter(p3) .map(n->n=new NominationWrapper(n.toString()))
                .collect(Collectors.toSet());


        if (!answer.isEmpty()) {

            for (NominationWrapper n:answer){
            final Violation violation = new Violation();
            violation.setDescription("Class name violation '" + n + "'");
            violation.setFileName(compilationUnit.getFileName());
            violation.setLine(n.getLine());
            addViolation(violation);
        }}

    }


    @Override
    public boolean isActive() {
        return true;
    }
}
