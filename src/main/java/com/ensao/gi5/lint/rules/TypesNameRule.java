package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.util.ViolationFactory;
import com.ensao.gi5.lint.visitor.NameVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.SimpleWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TypesNameRule extends Rule{

    //Le constructeur par défaut
    public TypesNameRule() {

        super(Constantes.LINT_REG_002, Level.HIGH);
    }

    //Implémentation de la première méthode abstraite
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        List<SimpleWrapper> listVariables = new ArrayList<>();

        compilationUnit.accept(new NameVisitors(), listVariables);

        //Implémentation de la règle LINT_REG_002
        listVariables.stream().filter(e -> e.isRuleOf(Constantes.LINT_REG_002)).forEach(var -> {
            String variableName = var.getSimpleName().asString();

            if(!Pattern.compile("^[A-Z].*").matcher(variableName).matches() || variableName.contains("_")){
                final Violation v = ViolationFactory.createViolation(Constantes.LINT_REG_002, compilationUnit,
                        Utils.getLine(var.getSimpleName()), var.getSimpleName());
                addViolation(v);
            }
        });

        //Implémentation des deux règles LINT_REG_003 et LINT_REG_004
        listVariables.stream().filter(e -> e.isRuleOf(Constantes.LINT_REG_003, Constantes.LINT_REG_004)).forEach(var -> {
            if(!Character.isLowerCase(var.getSimpleName().asString().charAt(0))){
                final Violation violation = ViolationFactory.createViolation(Constantes.LINT_REG_003, compilationUnit, Utils.getLine(var.getSimpleName()), var.getSimpleName());
                addViolation(violation);
            }
        });

        //Implémentation de la règle LINT_REG_005
        listVariables.stream().filter(e -> e.isRuleOf(Constantes.LINT_REG_005)).forEach(var -> {

            if(Pattern.compile("[^A-Z0-9_]").matcher(var.getSimpleName().asString()).find()) {

                final Violation violation = ViolationFactory.createViolation(Constantes.LINT_REG_005, compilationUnit, Utils.getLine(var.getSimpleName()), var.getSimpleName());

                addViolation(violation);
            }
        });

        //Implémentation de la règle LINT_REG_013
        listVariables.stream().filter(e -> e.isRuleOf(Constantes.LINT_REG_013)).forEach(var ->{

            if(var.getAccess().isEmpty()){
                final Violation violation = ViolationFactory.createViolation(Constantes.LINT_REG_013, compilationUnit, Utils.getLine(var.getSimpleName()), var.getSimpleName());
                addViolation(violation);
            }
        });



    }

    //Implémentation de la seconde méthode abstraite
    @Override
    public boolean isActive() {

        return true;
    }

}
