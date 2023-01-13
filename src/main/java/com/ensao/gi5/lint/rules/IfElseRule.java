package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.IfElseVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.GeneralStatementWrapper;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;



//les clauses if , else doivent avoir des accolades
public class IfElseRule  extends Rule{

    public IfElseRule() {
        super(Constantes.LINT_REG_018, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<GeneralStatementWrapper> ifElseWrapperList = new ArrayList<>();
        compilationUnit.accept(new IfElseVisitor(),ifElseWrapperList);

        for(GeneralStatementWrapper ifElseWrapper: ifElseWrapperList){
            Matcher matcher = Pattern.compile(".*\\{([\\S\\s]*)*\\}").matcher(ifElseWrapper.getStatement().toString());

            if(!matcher.find()){
                final Violation violation = ViolationFactory.ViolationFactory(
                        compilationUnit.getFileName(),
                        "If or Else statement is not valid , need curly braces ({})",
                        ifElseWrapper.getLine());
                addViolation(violation);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
