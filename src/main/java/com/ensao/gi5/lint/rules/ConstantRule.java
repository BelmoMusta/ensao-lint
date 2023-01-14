package com.ensao.gi5.lint.rules;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.SimpleName;

public class ConstantRule extends Rule{

	public ConstantRule() {
		super(Constantes.LINT_REG_005, Level.MEDIUM);
	}

	@Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		
        for (ClassOrInterfaceDeclaration clazz : compilationUnit.findAll1(ClassOrInterfaceDeclaration.class)) {
            for (FieldDeclaration field : clazz.getFields()) {
                for (VariableDeclarator variable : field.getVariables()) {
                    SimpleName variableName = variable.getName();
                    if (!variableName.asString().toUpperCase().equals(variableName.asString())) {
                        Violation violation = new Violation();
                        violation.setDescription("Constant name should be in uppercase: " + variableName.asString());
                        violation.setFileName(compilationUnit.getFileName());
                        violation.setLine(variable.getBegin().get().line);
                        addViolation(violation);
                    }
                    if (!variableName.asString().contains("_")) {
                        Violation violation = new Violation();
                        violation.setDescription("Constant name should contain at least one underscore: " + variableName.asString());
                        violation.setFileName(compilationUnit.getFileName());
                        violation.setLine(variable.getBegin().get().line);
                        addViolation(violation);
                    }
                }
            }
            
        }		
	}
	
	@Override
	public boolean isActive() {
		return true;
	}
	
}
