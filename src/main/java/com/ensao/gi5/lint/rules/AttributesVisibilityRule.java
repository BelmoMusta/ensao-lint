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
/**
 * AttributesVisibilityRule is a Java class that defines a rule for linting  of Java code.
 *  It is a specific rule for analyzing the visibility of attributes in a Java class.
 *  The linter is made up of two classes: AttributesVisibilityVisitor and AttributesVisibilityRule.
 *  The apply method checks for the visibility of attributes by using an instance of AttributesVisibilityVisitor to visit
 *  the CompilationUnitWrapper and adding any violations to a list of violations if the size of attributes list is != 0
 *  The AttributesVisibilityVisitor cheks all field declarations in a class if the field have no modifier(visibility) the name and line
 *  of the field are added to a list of RuleWrapper that will be returned by the visitor.
 *  The isActive method is overriding the parent class method, returns always true, it means that this rule is always active.
 * */
public class AttributesVisibilityRule extends Rule{
    public AttributesVisibilityRule() {
        super(Constantes.LINT_REG_013, Level.HIGHEST);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
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

    @Override
    public boolean isActive() {
        return true;
    }
}
