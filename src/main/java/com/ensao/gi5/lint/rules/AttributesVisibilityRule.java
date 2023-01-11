package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import java.io.File;
import java.util.List;

public class AttributesVisibilityRule extends Rule{
    public AttributesVisibilityRule() {
        super(Constantes.LINT_REG_013, Level.HIGHEST);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try{
            CompilationUnit unit = StaticJavaParser.parse(new File(compilationUnit.getFileName()));
            List<FieldDeclaration> fields = unit.findAll(FieldDeclaration.class);
            for (FieldDeclaration field : fields) {
                if(field.getModifiers().size()==0) {
                            final Violation violation = new Violation();
                            violation.setDescription("Attribute visibility warning");
                            violation.setFileName(compilationUnit.getFileName());
                            int line =field.getRange().isPresent()?field.getRange().get().begin.line:0;
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
