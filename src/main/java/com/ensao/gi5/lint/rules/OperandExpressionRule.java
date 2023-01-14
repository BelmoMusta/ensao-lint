package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;

public class OperandExpressionRule extends Rule{

	public OperandExpressionRule() {
		super(Constantes.LINT_REG_006, Level.HIGHEST);
	}
	
    @Override
	public void apply(CompilationUnitWrapper compilationUnit) {
		
		for(MethodDeclaration methode :  compilationUnit.getMethods()) {
			for(Statement statement : methode.getBody().get().getStatements()){
			if(statement instanceof IfStmt) {
				IfStmt stmt = (IfStmt) statement;
				
				if(stmt.getCondition().findAll(BinaryExpr.class).size()>2) {
					Violation violation = new Violation();
					violation.setDescription("if statement in method " + methode.getNameAsString() + "has more than 2 logical operands");
					violation.setFileName(compilationUnit.getFileName());
					violation.setLine(stmt.getBegin().get().line);
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
