package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ClauseIfElseVisitors;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.StatementWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//This class is a rule that checks if the ifElse blocks have {} ,
//It will return you a violation if it is not written with them .

public class ClauseIfEseRule extends Rule {

    public ClauseIfEseRule() {
        super(Constantes.LINT_REG_018, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<StatementWrapper> list = new ArrayList<>();
        compilationUnit.accept(new ClauseIfElseVisitors(), list);

        for(StatementWrapper l:list){
            Matcher matcher = Pattern.compile(".*\\{([\\S\\s]*)\\}").matcher(l.getStatement().toString());

            if(!matcher.find()){
                final Violation violation = new Violation();
                violation.setDescription("If Else clause is not valid ! It needs {}" );
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(l.getLine());
                addViolation(violation);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
