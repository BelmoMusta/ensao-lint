package com.ensao.gi5.lint.util;

import com.ensao.gi5.lint.rules.Level;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;


import java.util.ResourceBundle;

public class ViolationFactory {
    final static ResourceBundle strings = ResourceBundle.getBundle("strings");
    final static ResourceBundle levels = ResourceBundle.getBundle("levels");

    public static Violation create(String ruleId, CompilationUnitWrapper cu, int line, Object... args){
        final Violation violation = new Violation();
        violation.setDescription(String.format(strings.getString(ruleId), args));
        violation.setFileName(cu.getFileName());
        violation.setLine(line);
        violation.setRuleId(ruleId);
        violation.setLevel(Level.valueOf(levels.getString(ruleId)));
        return violation;
    }



}
