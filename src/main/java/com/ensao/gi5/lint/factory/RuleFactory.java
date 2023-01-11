package com.ensao.gi5.lint.factory;

import com.ensao.gi5.lint.enumeration.RulesEnum;
import com.ensao.gi5.lint.rules.*;

public class RuleFactory {
    public Rule getRule(RulesEnum ruleSubClass) {
        switch (ruleSubClass) {
            case UNUSED_IMPORT_STATEMENT:
                return new UnusedImportsRule();
            case PARSE_ERROR:
                return new ParseErrorRule();
            case CLASS_NAME:
                return new TypeNameRule();
            case BOOLEAN_EXPRESSION:
                return new BooleanExpressionRule();
            case METHOD_LINES:
                return new MethodLinesRule();
            case ATTRIBUTE_VISIBILITY:
                return new AttributeVisibilityRule();
            case ATTRIBUTE_NAME:
                return new AttributeNameRule();
            case CLASS_METHODS:
                return new ClassMethodsRule();
            default:
                return null;
        }
    }
}
