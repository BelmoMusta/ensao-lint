package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.visitor.CatchExceptionVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.GeneralStatementWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Hmama Mohammed
 * @version 1.0.0
 *
 * The CatchExceptionRule class is a Java class that extends the {Rule} abstract class.
 * It is used to check for the presence of logging statements in catch blocks within a compilation unit.
 * The apply method is an implementation of the abstract method in the {Rule} class.
 * It uses the CatchExceptionVisitor class to traverse the compilation unit and collect all catch blocks in a list.
 * It then iterates through the list and uses a regular expression to check if each catch block contains a logging statement.
 * If a catch block does not contain a logging statement, a violation is created using the ViolationFactory
 * class and added to the violations list using the addViolation method.
 *
 * This class is a rule that checks for the presence of logging statements in catch blocks of a compilation unit,
 * it will return a violation if the catch block does not have a logger.
 * **/
public class CatchExceptionRule extends Rule{
    public CatchExceptionRule() {
        super(Constantes.LINT_REG_015, Level.LOW);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        List<GeneralStatementWrapper> catchClauseWrapperList = new ArrayList<>();
        compilationUnit.accept(new CatchExceptionVisitor(),catchClauseWrapperList);

        for(GeneralStatementWrapper ifElseWrapper: catchClauseWrapperList){
            Matcher matcher = Pattern.compile("(printStackTrace)|(log)|(print)").matcher(ifElseWrapper.getStatement().toString());

            if(!matcher.find()){

                final Violation violation = ViolationFactory.ViolationFactory(
                                            compilationUnit.getFileName(),
                                    "Catch block don't have a logger",
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
