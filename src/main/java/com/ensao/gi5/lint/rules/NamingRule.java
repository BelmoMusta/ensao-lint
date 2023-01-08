package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.util.ViolationFactory;
import com.ensao.gi5.lint.visitor.NamesVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.SimpleWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class NamingRule extends Rule {

    public NamingRule() {
        super(Constantes.LINT_REG_003, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper cu) {
        List<SimpleWrapper> variables = new ArrayList<>();

        cu.accept(new NamesVisitor(), variables);

        //LINT_REG_002
        variables.stream().filter(e -> e.isRuleOf(Constantes.LINT_REG_002)).forEach(vr -> {
            String name = vr.getSimpleName().asString();
            if(!Pattern.compile("^[A-Z].*").matcher(name).matches() || name.contains("_")){
                final Violation v = ViolationFactory.create(Constantes.LINT_REG_002, cu,
                        Utils.getLine(vr.getSimpleName()), vr.getSimpleName());
                addViolation(v);
            }
        });

        //LINT_REG_003 and LINT_REG_004
        variables.stream().filter(e -> e.isRuleOf(Constantes.LINT_REG_003, Constantes.LINT_REG_004)).forEach(vr -> {
            if(!Character.isLowerCase(vr.getSimpleName().asString().charAt(0))){
                final Violation violation = ViolationFactory.create(Constantes.LINT_REG_003, cu, Utils.getLine(vr.getSimpleName()),
                        vr.getSimpleName());
                addViolation(violation);
            }
        });

        //LINT_REG_005
        variables.stream().filter(e->e.isRuleOf(Constantes.LINT_REG_005)).forEach(vr ->{
            if(Pattern.compile("[^A-Z0-9_]").matcher(vr.getSimpleName().asString()).find()){
                final Violation violation = ViolationFactory.create(Constantes.LINT_REG_005, cu, Utils.getLine(vr.getSimpleName()),
                        vr.getSimpleName());
                addViolation(violation);
            }
        });

        //LINT_REG_013
        variables.stream().filter(e->e.isRuleOf(Constantes.LINT_REG_013)).forEach(vr ->{
            if(vr.getAccessSpecifier().isEmpty()){
                final Violation violation = ViolationFactory.create(Constantes.LINT_REG_013, cu, Utils.getLine(vr.getSimpleName()),
                        vr.getSimpleName());
                addViolation(violation);
            }
        });

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
