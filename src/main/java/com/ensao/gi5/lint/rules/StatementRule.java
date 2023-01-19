package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.util.ViolationFactory;
import com.ensao.gi5.lint.visitor.StatementVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.StatementWrapper;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StatementRule extends Rule{
    final static String ANY_PATTERN = "[\\S\\s]+";
    final static String IF_PATTERN = "([\\S\\s])*(\\{[\\S\\s]*\\})";
    final static String CATCH_PATTERN = "(print|log)";
    final static String ANONYMOUS_PATTERN = "new [\\s\\S]+\\{[\\s\\S]*\\}";
    final static String BOOLEAN_PATTERN = "(([\\w\\d\\s.])+(==|!=|<|>|>=|<=)[\\w\\d\\s.]+([&\\|]{2})?)+";

    public StatementRule() {
        super(Constantes.LINT_REG_006, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper cu) {
        Map<String, List<StatementWrapper>> statements = new HashMap<>();
        cu.accept(new StatementVisitor(), statements);

        //LINT_REG_006
        Optional.ofNullable(statements.get(Constantes.LINT_REG_006)).ifPresent(stmts ->{
            for(StatementWrapper stmt : stmts ){
                Matcher matcher = Pattern.compile(BOOLEAN_PATTERN).matcher(stmt.getStatement().toString());
                if(matcher.find()){
                    if(matcher.group().split("&&|\\|\\|").length>2) {
                        addViolation(ViolationFactory.create(stmt.getRuleId(), cu, stmt.getLine()));
                    }
                }
            }
        });

        //LINT_REG_009
        checkViolationWithRegex(Constantes.LINT_REG_009, statements, cu, ANONYMOUS_PATTERN, true);

        //LINT_REG_010
        checkViolationWithRegex(Constantes.LINT_REG_010, statements, cu, ANY_PATTERN, true);

        //LINT_REG_015
        checkViolationWithRegex(Constantes.LINT_REG_015, statements, cu, CATCH_PATTERN, false);

        //LINT_REG_018
        checkViolationWithRegex(Constantes.LINT_REG_018, statements, cu, IF_PATTERN, false);



    }

    private void checkViolationWithRegex(String ruleId, Map<String, List<StatementWrapper>> statements, CompilationUnitWrapper cu, String regex, boolean value){
        Optional.ofNullable(statements.get(ruleId)).ifPresent(stmts ->{
            for(StatementWrapper stmt : stmts ){
                if(Pattern.compile(regex).matcher(stmt.getStatement().toString()).find() == value){
                    addViolation(ViolationFactory.create(stmt.getRuleId(), cu, stmt.getLine()));
                }
            }
        });
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
