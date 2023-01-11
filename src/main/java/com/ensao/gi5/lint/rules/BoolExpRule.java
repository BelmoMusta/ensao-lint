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

public class BoolExpRule extends Rule{

	  public BoolExpRule() {
	        super(Constantes.LINT_REG_006, Level.HIGHEST);
	    }

	    @Override
	    public void apply(CompilationUnitWrapper compilationUnit) {
	        List<StmtWrapper> BoolExpWrappers = new ArrayList<>();
	        compilationUnit.accept(new IfElseVisitor(),BoolExpWrappers);

	        for(StmtWrapper BoolExpWrapper: BoolExpWrappers){
	            Matcher matcher = Pattern.compile("(([\\\\w\\\\d\\\\s.])+(==|!=|<|>|>=|<=)[\\\\w\\\\d\\\\s.]+([&\\\\|]{2})?)+").matcher(BoolExpWrapper.getStatement().toString());

	            if(!matcher.find()){
	                final Violation violation = new Violation();
	                violation.setDescription("Boolean expressions must have no more than 2 operands" );
	                violation.setFileName(compilationUnit.getFileName());
	                violation.setLine(BoolExpWrapper.getLigne());
	                addViolation(violation);
	            }
	        }
	    }

	    @Override
	    public boolean isActive() {
	        return true;
	    }

}
