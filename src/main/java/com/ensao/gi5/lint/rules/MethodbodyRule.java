package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class MethodbodyRule extends Rule{
    public MethodbodyRule() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        final Set<Integer> body = new LinkedHashSet<>();
        compilationUnit.accept(new VoidVisitorAdapter<Set<Integer>>() {
            @Override
            public void visit(MethodDeclaration n, Set<Integer> arg) {

                arg.add(n.getEnd().get().line-n.getBegin().get().line);

                super.visit(n, arg);

            }
        }, body);

       Optional<Integer> c= body.stream().filter(p->p>30).findAny();
        if(c.isPresent()){
            final Violation violation = new Violation();
            violation.setDescription("Body Method  violation '" );
            violation.setFileName(compilationUnit.getFileName());
            //violation.setLine(n.getLine());
            addViolation(violation);
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
