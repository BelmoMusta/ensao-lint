package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

import java.io.File;
import java.util.List;

public class AttributesRule extends Rule{
    public AttributesRule() {
        super(Constantes.LINT_REG_004, Level.HIGH);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try{
            CompilationUnit unit = StaticJavaParser.parse(new File(compilationUnit.getFileName()));
            List<FieldDeclaration> fields = unit.findAll(FieldDeclaration.class);
            for (FieldDeclaration field : fields) {
                boolean isFinal = field.getModifiers().contains(Modifier.finalModifier());
                if(!isFinal) {
                    List<VariableDeclarator> attributes = field.getVariables();
                    for (VariableDeclarator attribute : attributes) {
                        String variableName = attribute.getNameAsString();
                        if (!Character.isLowerCase(variableName.charAt(0))) {
                            final Violation violation = new Violation();
                            violation.setDescription("Attributes error");
                            violation.setFileName(compilationUnit.getFileName());
                            int line =attribute.getRange().isPresent()?attribute.getRange().get().begin.line:0;
                            violation.setLine(line);
                            addViolation(violation);
                        }
                    }
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
