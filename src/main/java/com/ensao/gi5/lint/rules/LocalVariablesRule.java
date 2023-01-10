package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

import java.io.File;
import java.util.List;

public class LocalVariablesRule extends Rule {
    public LocalVariablesRule() {
        super(Constantes.LINT_REG_003, Level.HIGH);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try{
            CompilationUnit unit = StaticJavaParser.parse(new File(compilationUnit.getFileName()));
            List<MethodDeclaration> methods = unit.findAll(MethodDeclaration.class);
            for (MethodDeclaration method: methods
                 ) {
                List<VariableDeclarator> localVariables = method.findAll(VariableDeclarator.class);

                for (VariableDeclarator localVariable : localVariables) {
                    String locVar= localVariable.toString().split("=")[0] ;
                    if (!Character.isLowerCase(locVar.charAt(0))) {
                        final Violation violation = new Violation();
                        violation.setDescription("Local variable error");
                        violation.setFileName(compilationUnit.getFileName());
                        int line =localVariable.getRange().isPresent()?localVariable.getRange().get().begin.line:0;
                        violation.setLine(line);
                        addViolation(violation);
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println();
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
