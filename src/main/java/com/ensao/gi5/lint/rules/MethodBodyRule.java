package com.ensao.gi5.lint.rules;

import com.ensao.gi5.lint.constantes.Constantes;
import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.rules.violations.ViolationFactory;
import com.ensao.gi5.lint.util.Utils;
import com.ensao.gi5.lint.visitor.MethodBodyVisitor;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * The MethodBodyRule class is a Java class that extends the Rule abstract class.
 * It is used to check the size of the body of methods within a compilation unit.
 *
 * The apply method is an implementation of the abstract method in the Rule class.
 * It uses the MethodBodyVisitor class to traverse the compilation unit and collect
 * all method declarations in a set. It then filters the set of methods by checking
 * if the number of lines in the method body is greater than 30.
 * If a method body contains more than 30 lines, a violation is created using the
 * ViolationFactory class and added to the violations list using the addViolation method.
 *
 * This class is a rule that checks the size of the body of methods within a compilation unit.
 * It will return a violation if the method body contains more than 30 lines.
 *
 * **/
public class MethodBodyRule extends Rule{
    public MethodBodyRule() {
        super(Constantes.LINT_REG_008, Level.HIGHEST);
    }

    @Override
    public void apply(CompilationUnitWrapper compilationUnit) {
        Set<MethodDeclaration> allMethodesBody = new LinkedHashSet<>();
        compilationUnit.accept(new MethodBodyVisitor(), allMethodesBody);
        allMethodesBody.stream().filter(m -> (m.getEnd().get().line - m.getBegin().get().line + 1) > 30)
                .forEach(oneMethode->{
            final Violation violation = ViolationFactory.ViolationFactory(
                    compilationUnit.getFileName(),
                    "La methode  '" + oneMethode.getNameAsString()+ "' de la classe '"+ Utils.findClassName(oneMethode) +"' contain more than 30 line !",
                    2);
            addViolation(violation);
        });
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
