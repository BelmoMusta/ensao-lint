package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.AnonymousVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import java.io.File;
import java.util.Hashtable;

public class AnonymousRule extends Rule{
    public AnonymousRule() {
        super(Constantes.LINT_REG_009, Level.HIGH);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try{
            CompilationUnit unit = StaticJavaParser.parse(new File(compilationUnit.getFileName()));
            Hashtable<String,Integer> anonymousInstanciations = new Hashtable<>();
            unit.accept(new AnonymousVisitor(anonymousInstanciations), null);
            if(anonymousInstanciations.size()!=0){
                for (String miss : anonymousInstanciations.keySet()) {
                    final Violation violation = new Violation();
                    violation.setDescription("Anonymous inner class found please use a lambda expression instead.");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(anonymousInstanciations.get(miss));
                    addViolation(violation);
                }
            }
        }
        catch (Exception e){
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
