package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.AnonymousVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import java.util.Hashtable;

public class AnonymousRule extends Rule{
    public AnonymousRule() {
        super(Constantes.LINT_REG_009, Level.HIGH);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try{
            Hashtable<String,Integer> anonymousInstanciations = new Hashtable<>();
            compilationUnit.accept(new AnonymousVisitor(anonymousInstanciations), null);
            if(anonymousInstanciations.size()!=0){
                for (String miss : anonymousInstanciations.keySet()) {
                    Violation violation=ViolationMaker.makeViolation(compilationUnit.getFileName()
                            ,"Anonymous inner class found please use a lambda expression instead."
                            , anonymousInstanciations.get(miss));
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
