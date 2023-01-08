package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;

import java.util.List;

public class MethodBodySizeRule extends Rule{

    private int maxLines = 30;

    public MethodBodySizeRule() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {

        final List<MethodDeclaration> methodDeclarations = compilationUnit.getMethods();
        for (MethodDeclaration m : methodDeclarations) {
            BlockStmt body = m.getBody().orElse(null);
            if (body != null) {
                int lineCount = body.getEnd().get().line - body.getBegin().get().line + 1;
                if (lineCount > this.maxLines) {
                    final Violation violation = new Violation();
                    violation.setDescription("Method '" + m.getNameAsString() + "' Body max lines exceded");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(body.getBegin().get().line + this.maxLines + 1);
                    addViolation(violation);
                }
            }
        }

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
