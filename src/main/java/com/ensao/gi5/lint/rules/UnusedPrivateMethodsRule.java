package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.UnusedPrivateMethodsVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import java.io.File;
import java.util.Hashtable;
public class UnusedPrivateMethodsRule extends Rule{
    public UnusedPrivateMethodsRule() {
        super(Constantes.LINT_REG_017, Level.MEDIUM);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try{
            CompilationUnit unit = StaticJavaParser.parse(new File(compilationUnit.getFileName()));
            Hashtable<String,Integer> unsedMethods = new Hashtable<>();
            unit.accept(new UnusedPrivateMethodsVisitor(unsedMethods), null);
            if(unsedMethods.size()!=0){
                for (String method : unsedMethods.keySet()) {
                        final Violation violation = new Violation();
                        violation.setDescription("Unused Private Methods");
                        violation.setFileName(compilationUnit.getFileName());
                        violation.setLine(unsedMethods.get(method));
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
