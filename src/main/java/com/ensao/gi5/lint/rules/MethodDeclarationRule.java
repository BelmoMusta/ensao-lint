package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.visitor.ConstantesAttributesVisitor;
import com.ensao.gi5.lint.visitor.MethodDeclarationVisitor;
import com.ensao.gi5.lint.wrapper.ClassAttributesWrapper;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.LinkedHashSet;
import java.util.Set;

public class MethodDeclarationRule extends Rule{

    public MethodDeclarationRule(){
        super(Constantes.LINT_REG_008,Level.HIGHEST);
    }
    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<MethodDeclaration> methodDeclarationSet = new LinkedHashSet<MethodDeclaration>();
        compilationUnit.accept(new MethodDeclarationVisitor(),methodDeclarationSet);

        methodDeclarationSet.stream()
                .filter(method -> method.getEnd().map(p->p.line).orElse(-1) - method.getBegin().map(p->p.line).orElse(-1) + 1 <=30)
                .forEach(field -> {
                    final Violation violation = new Violation();
                    violation.setDescription(field.toString() + "depasse 30 lignes");
                    violation.setFileName(compilationUnit.getFileName());
                    violation.setLine(field.getBegin().map(p ->p.line).orElse(-1));
                    addViolation(violation);
                });

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
