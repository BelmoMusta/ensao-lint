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
/**
 * This code defines a rule class called "ClassNameRule" that extends the "Rule" class.
 *  The "apply" method takes a "CompilationUnitWrapper" object and uses it to check for the name of all classes in the code.
 * The method iterates over all the types in the compilation unit,
 * it checks whether the first letter of the type's name is uppercase and if the name contains an underscore "_",
 * if either of these conditions is true, it creates a new "Violation" object and sets its description,
 * file name, and line number as arguments and then add this violation to the list of violations.
 * The isActive method is overriding the parent class method, returns always true, it means that this rule is always active.
 * */
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
