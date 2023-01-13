package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.CatchExceptionVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.GeneralStatementWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                final Violation violation = new Violation();
                violation.setDescription("Catch block don't have a logger" );
                violation.setFileName(compilationUnit.getFileName());
                violation.setLine(ifElseWrapper.getLine());
                addViolation(violation);
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
