package com.ensao.gi5.lint.factory;

import com.ensao.gi5.lint.enumeration.RulesEnum;
import com.ensao.gi5.lint.rules.*;

public class RuleFactory {
    public Rule getRule(RulesEnum ruleSubClass) {
        return switch(ruleSubClass) {
            case UNUSED_IMPORT_STATEMENT -> new UnusedImportsRule();
            case PARSE_ERROR -> new ParseErrorRule();
            case CLASS_NAME -> new TypeNameRule();
            case BOOLEAN_EXPRESSION -> new BooleanExpressionRule();
            case METHOD_LINES -> new MethodStatementsRule();
            case ATTRIBUTE_VISIBILITY -> new AttributeVisibilityRule();
            case ATTRIBUTE_NAME -> new AttributeNameRule();
            case CLASS_METHODS -> new ClassMethodsRule();
        };
    }
}
