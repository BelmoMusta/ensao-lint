package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.util.ViolationFactory;
import com.ensao.gi5.lint.visitor.NamesVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.SimpleWrapper;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class NamingRule extends Rule {

    public NamingRule() {
        super(Constantes.LINT_REG_003, Level.HIGH);
    }

    private void checkViolationWithPredicate(String ruleId, CompilationUnitWrapper cu, Map<String, List<SimpleWrapper>> variables, Predicate<SimpleWrapper> predicate){
        Optional.ofNullable(variables.get(ruleId)).ifPresent(vars -> {
            vars.forEach(vr -> {
                if(predicate.test(vr)){
                    final Violation v = ViolationFactory.create(ruleId, cu,
                            Utils.getLine(vr.getSimpleName()), vr.getSimpleName());
                    addViolation(v);
                }
            });
        });
    }

    private void checkViolationWithRegex(String ruleId, CompilationUnitWrapper cu, Map<String, List<SimpleWrapper>> variables, String regex){
        checkViolationWithPredicate(ruleId, cu, variables, vr -> !Pattern.compile(regex).matcher(vr.getSimpleName().asString()).matches());
    }

    @Override
    public void apply(CompilationUnitWrapper cu) {

        Map<String, List<SimpleWrapper>> variables = new HashMap<>();

        cu.accept(new NamesVisitor(), variables);

        //LINT_REG_002
        checkViolationWithRegex(Constantes.LINT_REG_002, cu, variables, "^[A-Z][A-Za-z0-9]*");


        //LINT_REG_003
        checkViolationWithRegex(Constantes.LINT_REG_003, cu, variables, "^[a-z]\\w*");

        //LINT_REG_004
        checkViolationWithRegex(Constantes.LINT_REG_004, cu, variables, "^[a-z]\\w*");

        //LINT_REG_005
        checkViolationWithRegex(Constantes.LINT_REG_005, cu, variables, "[A-Z0-9_]+");

        //LINT_REG_013
        checkViolationWithPredicate(Constantes.LINT_REG_013, cu, variables, vr->
                vr.getAccessSpecifier().isEmpty()
        );

    }


    @Override
    public boolean isActive() {
        return true;
    }
}
