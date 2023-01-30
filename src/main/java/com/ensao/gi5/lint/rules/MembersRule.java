package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.LocalvarVisitors;
import com.ensao.gi5.lint.visitor.MembersVisitors;
import com.ensao.gi5.lint.visitor.NominationVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.NominationWrapper;
import com.github.javaparser.ast.body.BodyDeclaration;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MembersRule extends Rule{
    public MembersRule() {
        super(Constantes.LINT_REG_004, Level.HIGHEST);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        final Set<String> m = new LinkedHashSet<>();
        compilationUnit.accept(new MembersVisitors(), m);



        Predicate<Object> p1= p->(Character.isUpperCase(p.toString().charAt(1)));



        Set<String> answer=m.stream().filter(p1)
                .collect(Collectors.toSet());


        if (!answer.isEmpty()) {

            for (String n:answer){
                final Violation violation = new Violation();
                violation.setDescription("Class name violation '" + n + "'");
                violation.setFileName(compilationUnit.getFileName());
                //violation.setLine(n.getLine());
                addViolation(violation);
            }}


    }

    @Override
    public boolean isActive() {
        return true;
    }
}
