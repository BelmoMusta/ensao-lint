package com.ensao.gi5.lint.util;

import com.ensao.gi5.lint.rules.Level;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;

import java.util.Objects;
import java.util.ResourceBundle;

public class ViolationFactory {

    final static ResourceBundle levelBundles = ResourceBundle.getBundle("levelBundles");
    final static ResourceBundle stringBundles = ResourceBundle.getBundle("stringBundles");

    public static Violation createViolation(String ruleId, CompilationUnitWrapper compilationUnitWrapper, int line, Object... args) {

        final Violation violation = new Violation();

        violation.setDescription(String.format(stringBundles.getString(ruleId), args));
        violation.setFileName(compilationUnitWrapper.getFileName());
        violation.setLevel(Level.valueOf(levelBundles.getString("levelBundles")));
        violation.setLine(line);
        violation.setRuleId(ruleId);

        return violation;
    }


}
