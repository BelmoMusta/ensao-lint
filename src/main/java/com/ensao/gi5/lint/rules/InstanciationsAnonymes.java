package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class InstanciationsAnonymes extends Rule{
    public InstanciationsAnonymes() {
        super(Constantes.LINT_REG_009, Level.HIGH);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        compilationUnit.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(ObjectCreationExpr n, Void arg) {
                if (n.getAnonymousClassBody().isPresent()) {
                    final Violation violation = new Violation();
                    violation.setDescription("Anonymous class should be replaced by lambda expression");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(n.getBegin().get().line);
                    addViolation(violation);
                }
                super.visit(n, arg);
            }
        }, null);
    }


    @Override
    public boolean isActive() {
        return true;
    }
}
