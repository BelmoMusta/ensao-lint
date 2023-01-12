package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ConstantVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import java.io.File;
import java.util.Hashtable;
public class ConstantsRule extends Rule{
    public ConstantsRule() {
        super(Constantes.LINT_REG_005, Level.MEDIUM);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try{
            CompilationUnit unit = StaticJavaParser.parse(new File(compilationUnit.getFileName()));
            Hashtable<String,Integer> constantNaming = new Hashtable<>();
            unit.accept(new ConstantVisitor(constantNaming), null);
            if(constantNaming.size()!=0){
                for (String miss : constantNaming.keySet()) {
                    final Violation violation = new Violation();
                    violation.setDescription("Constant naming error");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(constantNaming.get(miss));
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