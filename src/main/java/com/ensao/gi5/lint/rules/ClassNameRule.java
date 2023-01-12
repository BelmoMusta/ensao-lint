package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClassNameRule extends Rule {

    public ClassNameRule() {
        super(Constantes.LINT_REG_002, Level.HIGHEST);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
            try{
            CompilationUnit unit = compilationUnit.getParser();
            for (TypeDeclaration<?> type : unit.getTypes()
            ){
                String typeName =type.getNameAsString();
                if(!Character.isUpperCase(typeName.charAt(0)) || typeName.contains("_")){
                    final Violation violation = new Violation();
                    violation.setDescription("Naming error");
                    violation.setFileName(compilationUnit.getFileName());
                    int line =type.getRange().isPresent()?type.getRange().get().begin.line:0;
                    violation.setLine(line);
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
