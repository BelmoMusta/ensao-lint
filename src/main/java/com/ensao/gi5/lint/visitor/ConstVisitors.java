package com.ensao.gi5.lint.visitor;


import com.ensao.gi5.lint.wrapper.NominationWrapper;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Set;

public class ConstVisitors  extends VoidVisitorAdapter<Set<String>> {
    public static boolean containsWords(String inputString, String[] items) {
        boolean found = true;
        for (String item : items) {
            if (!inputString.contains(item)) {
                found = false;
                break;
            }
        }
        return found;
    }
    @Override
    public void visit(ClassOrInterfaceDeclaration classe, Set<String> arg) {
        String[] words = {"static", "final"};
        NodeList<BodyDeclaration<?>> members = classe.getMembers();

        for (int i = 0; i < members.size(); i++) {
            if (members.get(i) instanceof FieldDeclaration) {
if(containsWords(members.get(i).toString(),words  ) ){
                arg.add(((FieldDeclaration) members.get(i)).getVariables().toString());
              // System.out.println(((FieldDeclaration) members.get(i)).getModifiers().toString());
}
            }
        }
        super.visit(classe, arg);
    }





}




