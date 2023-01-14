package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.IfElseVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.GeneralStatementWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The IfElseRule class is a Java class that extends the Rule abstract class.
 * It is used to check the format of if-else statements within a compilation unit.
 *
 * The apply method is an implementation of the abstract method in the Rule class.
 * It uses the IfElseVisitor class to traverse the compilation unit and collect
 * all if-else statements in a list. It then iterates through the list of statements
 * and uses a regular expression to check if the statement contains curly braces.
 * If an if-else statement does not contain curly braces, a violation is created using
 * the ViolationFactory class and added to the violations list using the addViolation method.
 *
 * This class is a rule that checks the format of if-else statements within a compilation unit.
 * It will return a violation if the if-else statement does not contain curly braces.
 * **/
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
