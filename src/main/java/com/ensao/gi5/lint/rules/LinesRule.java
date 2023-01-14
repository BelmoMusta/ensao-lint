package com.ensao.gi5.lint.rules;
import java.util.LinkedHashSet;
import java.util.Set;
import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.LinesVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.LinesWrapper;


public class LinesRule extends Rule {
    public LinesRule() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<LinesWrapper> linesWrappers = new LinkedHashSet<>();
        compilationUnit.accept(new LinesVisitor(), linesWrappers);
        for (LinesWrapper linesWrapper : linesWrappers) {
            int numberOfLines = linesWrapper.getEndLine() - linesWrapper.getStartLine();
            if (numberOfLines > 30) {
                Violation violation = new Violation();
                violation.setFileName(compilationUnit.getFileName());
                violation.setDescription("The body of a method shouldn't exceed 30 lines");
                violation.setLine(linesWrapper.getLine());
                addViolation(violation);
            }
        }
    }



    @Override
    public boolean isActive() {
        return true;
    }
}