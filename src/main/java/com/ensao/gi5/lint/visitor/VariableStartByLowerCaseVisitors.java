package com.ensao.gi5.lint.visitor;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ensao.gi5.lint.wrapper.VariableStartByLowerCaseWrapper;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class VariableStartByLowerCaseVisitors extends VoidVisitorAdapter<Set<VariableStartByLowerCaseWrapper>> {
	  @Override
	    public void visit(CompilationUnit compilationUnit, Set<VariableStartByLowerCaseWrapper> arg) {
		  NodeList<Statement> statements = null;
		  for (TypeDeclaration typeDec : compilationUnit.getTypes()) {
	          List<BodyDeclaration> members = typeDec.getMembers();
	          if (members != null) {
	              for (BodyDeclaration member : members) {
	                  if (member.isMethodDeclaration()) {   // If it is a method variable
	                      MethodDeclaration method = (MethodDeclaration) member;
	                      Optional<BlockStmt> block = method.getBody();	 
	                       statements = block.get().getStatements();
	                  }
	              }
	             
	          }
	      }
		  if(statements != null) {
		  for(Statement statement : statements) {
			  arg.add(new VariableStartByLowerCaseWrapper(statement ));
		  }}
		   
		  super.visit(compilationUnit, arg);
	    }
	 

}
