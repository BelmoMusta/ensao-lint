package com.ensao.gi5.lint.rules.namingRules;

import com.ensao.gi5.lint.rules.Level;
import com.ensao.gi5.lint.rules.Rule;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.NameWrapper;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public abstract class GeneralNamingRule extends Rule {

    final static ResourceBundle Levels =ResourceBundle.getBundle("Levels");

    public GeneralNamingRule(String id, Level level) {
        super(id, level);
    }

    public void checkNamingRules(Map<String, List<NameWrapper>> var, Predicate<NameWrapper> predicate, String ruleId, CompilationUnitWrapper compilationUnit){
        Optional.ofNullable(var.get(ruleId)).ifPresent(list-> list.forEach(v->{
            if(predicate.test(v)) {
                addViolation(violationBuilder.setDescription(ruleId)
                        .setRuleId(ruleId)
                        .setFileName(compilationUnit.getFileName())
                        .setLevel(Level.valueOf(Levels.getString(ruleId)))
                        .setLine(Utils.getLine(v.getSimpleName())).build());
            }
        }));



    }
}
