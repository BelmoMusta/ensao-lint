package com.ensao.gi5.lint.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.IfElseVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.StmtWrapper;

public class IfElseRule extends Rule{

	  public IfElseRule() {
	        super(Constantes.LINT_REG_018, Level.LOW);
	    }

	    @Override
	    public void apply(CompilationUnitWrapper compilationUnit) {
	        List<StmtWrapper> ifElseWrappers = new ArrayList<>();
	        compilationUnit.accept(new IfElseVisitor(),ifElseWrappers);

	        for(StmtWrapper ifElseWrapper: ifElseWrappers){
	            Matcher matcher = Pattern.compile(".*\\{([\\S\\s]*)\\}").matcher(ifElseWrapper.getStatement().toString());

	            if(!matcher.find()){
	                final Violation violation = new Violation();
	                violation.setDescription("if/else clauses must have braces" );
	                violation.setFileName(compilationUnit.getFileName());
	                violation.setLine(ifElseWrapper.getLigne());
	                violation.setRuleId(Constantes.LINT_REG_018);
	                violation.setLevel(Level.LOW);
	                addViolation(violation);
	            }
	        }
	    }

	    @Override
	    public boolean isActive() {
	        return true;
	    }

}
