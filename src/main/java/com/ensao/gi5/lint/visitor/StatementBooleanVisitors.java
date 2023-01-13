package com.ensao.gi5.lint.visitor;

import java.util.List;
import java.util.regex.Pattern;
import com.ensao.gi5.lint.wrapper.StatementWrapper;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class StatementBooleanVisitors extends VoidVisitorAdapter<List<StatementWrapper>> {
	
	 @Override
	    public void visit(BlockStmt blockStmt,  List<StatementWrapper> arg) {	       
		 blockStmt.getStatements().stream().filter(s -> Pattern.compile("&&|\\|\\|")
	                .matcher(s.toString()).find()).forEach(s ->
	                    arg.add(new StatementWrapper(s))
	                    
	                    
	                
	        );
	        super.visit(blockStmt, arg);
	    }

}
