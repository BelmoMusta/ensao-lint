package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.util.ViolationFactory;
import com.ensao.gi5.lint.visitor.StatementVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.StatementWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatementRule extends Rule{

    //Les patterns
    final static String IF = "([\\S\\s])*(\\{[\\S\\s]*\\})";
    final static String ANONYMOUS = "new [\\s\\S]+\\{[\\s\\S]*\\}";
    final static String ANY = "[\\S\\s]+";
    final static String BOOLEAN = "(([\\w\\d\\s])+(==|!=|<|>|>=|<=)[\\w\\d\\s]+([&\\|]{2})?)+";
    final static String CATCH = "(print|log)";

    //Le constructeur par défaut
    public StatementRule() {

        super(Constantes.LINT_REG_006, Level.LOW);
    }

    //La redéfinition de la méthode apply
    @Override
    public void apply(CompilationUnitWrapper compilationUnitWrapper) {

        Map<String, List<StatementWrapper>> statements = new HashMap<>();
        compilationUnitWrapper.accept(new StatementVisitor(), statements);

        //L'implémentation de la méthode LINT_REG_006
        Optional.ofNullable(statements.get(Constantes.LINT_REG_006)).ifPresent(stats -> {

            for(StatementWrapper statementWrapper : stats) {

                Matcher matcher = Pattern.compile(BOOLEAN).matcher(statementWrapper.getStatement().toString());

                if(matcher.find()){
                    if(matcher.group().split("&&|\\|\\|").length>2) {
                        addViolation(ViolationFactory.createViolation(statementWrapper.getRuleId(), compilationUnitWrapper, statementWrapper.getLine()));
                    }
                }
            }
        });

        //L'implémentation de la règle LINT_REG_009
        checkViolationPattern(Constantes.LINT_REG_009, statements, compilationUnitWrapper, ANONYMOUS, true);

        //L'implémentation de la règle LINT_REG_010
        checkViolationPattern(Constantes.LINT_REG_010, statements, compilationUnitWrapper, ANY, true);

        //L'implémentation de la règle LINT_REG_015
        checkViolationPattern(Constantes.LINT_REG_015, statements, compilationUnitWrapper, CATCH, true);

        //L'implémentation de la règle LINT_REG_018
        checkViolationPattern(Constantes.LINT_REG_018, statements, compilationUnitWrapper, IF, true);

    }

    //La méthode checkViolationPattern()
    private void checkViolationPattern(String ruleId, Map<String, List<StatementWrapper>> statements, CompilationUnitWrapper cu, String regex, boolean value){
        Optional.ofNullable(statements.get(ruleId)).ifPresent(stmts ->{

            for(StatementWrapper statementWrapper : stmts ){
                if(Pattern.compile(regex).matcher(statementWrapper.getStatement().toString()).find() == value){
                    addViolation(ViolationFactory.createViolation(statementWrapper.getRuleId(), cu, statementWrapper.getLine()));
                }
            }
        });
    }

    @Override
    public boolean isActive() {
        return true;
    }

}
