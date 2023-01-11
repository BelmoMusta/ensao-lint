package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.EnumerationVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import java.io.File;
import java.util.Hashtable;

public class EnumerationRule extends Rule{
    public EnumerationRule() {
        super(Constantes.LINT_REG_007, Level.LOW);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try{
            CompilationUnit unit = StaticJavaParser.parse(new File(compilationUnit.getFileName()));
            Hashtable<String,Integer> enumerationNaming = new Hashtable<>();
            unit.accept(new EnumerationVisitor(enumerationNaming), null);
            if(enumerationNaming.size()!=0){
                for (String miss : enumerationNaming.keySet()) {
                    final Violation violation = new Violation();
                    violation.setDescription("Enumeration naming error");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(enumerationNaming.get(miss));
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
