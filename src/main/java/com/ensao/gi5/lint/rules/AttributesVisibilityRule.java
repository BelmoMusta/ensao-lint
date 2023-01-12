package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationMaker;
import com.ensao.gi5.lint.visitor.AttributeVisitor;
import com.ensao.gi5.lint.visitor.AttributesVisibilityVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.RuleWrapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AttributesVisibilityRule extends Rule{
    public AttributesVisibilityRule() {
        super(Constantes.LINT_REG_013, Level.HIGHEST);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        try{

            List<RuleWrapper> attributes = new ArrayList<>();
            compilationUnit.accept(new AttributesVisibilityVisitor(attributes),null);
            if(attributes.size()!=0){
                for(RuleWrapper attribute:attributes){
                    Violation violation = ViolationMaker.makeViolation(
                            compilationUnit.getFileName(),
                            "Attribute visibility warning",
                            attribute.getLine()
                    );
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
