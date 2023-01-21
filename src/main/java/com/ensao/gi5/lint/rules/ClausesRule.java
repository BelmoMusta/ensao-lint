package com.ensao.gi5.lint.rules;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;

import java.util.Optional;

public class ClausesRule extends Rule {

    public ClausesRule() {
        super(Constantes.LINT_REG_018, Level.LOW);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        for (IfStmt clause : compilationUnit.getCompilationUnit().findAll(IfStmt.class)) {
            Statement ifClause = clause.getThenStmt();
            Optional<Statement> elseClause = clause.getElseStmt();
            if (!(ifClause instanceof BlockStmt)) {
                final Violation violation = new Violation();
                violation.setDescription("les clauses if , doit avoir des accolades");
                violation.setFileName(compilationUnit.getFileName());
                addViolation(violation);
            }
            if (elseClause.isPresent() && !(elseClause.get() instanceof BlockStmt)) {
                final Violation violation = new Violation();
                violation.setDescription("les clauses else, doit avoir des accolades");
                violation.setFileName(compilationUnit.getFileName());
                addViolation(violation);
            }
        }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
