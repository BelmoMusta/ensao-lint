package com.ensao.gi5.lint.visitor;

import com.ensao.gi5.lint.wrapper.VariableWrapper;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnusedVariablesVisitor extends VoidVisitorAdapter<List<VariableWrapper>> {



    @Override
    public void visit(MethodDeclaration n, List<VariableWrapper> arg) {
        n.getBody().ifPresent(b -> b.getStatements().forEach(st ->{
            String vr = variableDeclaration(st);
            if(vr != null){
                VariableWrapper w = new VariableWrapper(vr, n.getNameAsString()
                        , resolveClassName(n), variableCount(b, vr), st.getBegin().map(p ->p.line).orElse(-1));
                arg.add(w);
            }
        }));
        super.visit(n, arg);
    }

    private String variableDeclaration(Statement statement){
        Matcher m = Pattern.compile("[\\w]+\\s+([\\w]+)\\s*(=\\s*[\\w\\s()]+)?;").matcher(statement.toString().replace("return", ""));
        if(m.find()) return m.group(1);
        else return null;
    }

    private int variableCount(BlockStmt blockStmt, String variable){
        int count = 0;
        Matcher matcher = Pattern.compile("\\b"+variable+"\\b").matcher(blockStmt.toString());
        while (matcher.find()){
            count++;
        }
        return count;
    }

    private String resolveClassName(MethodDeclaration m){
        final String[] fqn = {null};
        m.getParentNode().ifPresent(p->{
            fqn[0] = ((ClassOrInterfaceDeclaration) p).getFullyQualifiedName().orElse(null);
        });
        return fqn[0];
    }
}
