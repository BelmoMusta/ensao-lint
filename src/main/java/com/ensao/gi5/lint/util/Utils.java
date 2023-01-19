package com.ensao.gi5.lint.util;

import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.SimpleName;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

public class Utils {
     Utils() {
        throw new IllegalStateException("not to be instantiated");
    }

    //Methodes pour trouver le nom d'une classe a partir de son attribut/methode
    /**
     * @param fieldDeclaration
     * @return name of the class
     * This is a static method called findClassName that takes in a FieldDeclaration object
     * as its parameter. The method uses the getParentNode() method to access the parent node
     * of the field declaration, which should be a ClassOrInterfaceDeclaration object.
     *
     * It then uses the getFullyQualifiedName() method to retrieve the fully qualified name
     * of the class or interface, which is stored in a String array called nameOfClass.
     *
     * Finally, the method returns the value stored in nameOfClass[0], which is the fully
     * qualified name of the class or interface that contains the field declaration.
     * The method is used to find the class name that contain a given field.
     */
    public static String findClassName(FieldDeclaration fieldDeclaration){
        final String[] nameOfClass = new String[1];
        fieldDeclaration.getParentNode().ifPresent(p->{
            nameOfClass[0] = ((ClassOrInterfaceDeclaration) p).getFullyQualifiedName().orElse(null);
        });
        return nameOfClass[0];
    }

    /**
     * @param enumDeclaration
     * @return name of the enumeration
     *
     * This is a static method called findClassName that takes in a EnumConstantDeclaration
     * object as its parameter. The method uses the getParentNode() method to access the
     * parent node of the EnumConstantDeclaration, which should be an EnumDeclaration object.
     *
     * It then uses the getFullyQualifiedName() method to retrieve the fully qualified name
     * of the enum, which is stored in a String array called nameOfEnum.
     *
     * Finally, the method returns the value stored in nameOfEnum[0], which is the fully qualified
     * name of the enum that contains the EnumConstantDeclaration.
     * The method is used to find the enum name that contain a given enum constant.
     */
    public static String findClassName(EnumConstantDeclaration enumDeclaration) {
        final String[] nameOfEnum = new String[1];
        enumDeclaration.getParentNode().ifPresent(p->{
            nameOfEnum[0] = ((EnumDeclaration) p).getFullyQualifiedName().orElse(null);
        });
        return nameOfEnum[0];
    }

    /**
     * @param constructorDeclaration
     * @return name of class
     *
     * This method, findClassName(ConstructorDeclaration constructorDeclaration), is used to find the fully
     * qualified name of the class that a given ConstructorDeclaration object belongs to.
     *
     * It uses the getParentNode() method of the ConstructorDeclaration object to access the parent node
     * of the constructor, which is expected to be a ClassOrInterfaceDeclaration object.
     * The fully qualified name of this class is then retrieved using the getFullyQualifiedName()
     * method and stored in a local variable nameOfEnum. The method returns the value stored in nameOfEnum.
     *
     *  This method can be used in conjunction with the NumberOfParametersRule class to check for the
     * number of parameters in a constructor and report violations if the number exceeds a certain threshold.
     */
    public static String findClassName(ConstructorDeclaration constructorDeclaration) {
        final String[] nameOfClass = new String[1];
        constructorDeclaration.getParentNode().ifPresent(p->{
            nameOfClass[0] = ((ClassOrInterfaceDeclaration) p).getFullyQualifiedName().orElse(null);
        });
        return nameOfClass[0];
    }

    /**
     * @param methodDeclaration
     * @return name of the class
     *
     * This method findClassName takes a MethodDeclaration object as an argument and returns the fully qualified name
     * of the class that the method belongs to. It uses the getParentNode method from the MethodDeclaration
     * object to get the parent node, which should be a ClassOrInterfaceDeclaration object.
     *
     * It then uses the getFullyQualifiedName method from the ClassOrInterfaceDeclaration object to get
     * the fully qualified name of the class and assigns it to a variable nameOfClass.
     * This variable is returned at the end of the method.
     */
    public static String findClassName(MethodDeclaration methodDeclaration) {
        final String[] nameOfClass = new String[1];
        methodDeclaration.getParentNode().ifPresent(p->{
            nameOfClass[0] = ((ClassOrInterfaceDeclaration) p).getFullyQualifiedName().orElse(null);
        });
        return nameOfClass[0];
    }

    //Creation des methodes de generation de sortie
    public static void writeCsv(Collection<Violation> violations, Writer writer) throws IOException {
        for (Violation violation : violations) {
            writer.append(violation.toString()).append('\n');
        }
        violations.stream().map(Violation::toString).forEach(violation -> {
            try {
                writer.write(violation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.flush();
        writer.close();
    }


    public static void writeHtml(Collection<Violation> violations, Writer writer) throws IOException {
        for (Violation violation : violations) {
            writer.append(violation.toString()).append('\n');
        }
    }

    public static void writeMarkdown(Collection<Violation> violations, Writer writer) throws IOException {
        for (Violation violation : violations) {
            writer.append(violation.toString()).append('\n');
        }
    }

    public static String convertFQNToSimpleClassName(String fqn) {
        final String result;
        if (fqn == null) {
            result = null;
        } else {
            int lastIndexOfDot = fqn.lastIndexOf('.');
            if (lastIndexOfDot > 0) {
                result = fqn.substring(lastIndexOfDot + 1);
            } else {
                result = fqn;
            }
        }
        return result;
    }

    public static CompilationUnitWrapper getCompilationUnit(File file) {
        try {
            JavaParser javaParser = new JavaParser();
            ParseResult<CompilationUnit> result = javaParser.parse(file);
            return result.getResult()
                    .map(cu -> {
                        CompilationUnitWrapper compilationUnitWrapper = new CompilationUnitWrapper(cu, file.getAbsolutePath());
                        compilationUnitWrapper.addProblems(result.getProblems());
                        return compilationUnitWrapper;
                    })
                    .orElse(new CompilationUnitWrapper(new CompilationUnit(), file.getAbsolutePath()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Collection<File> getFilesFromDirectory(String directoryPath) {
        return getFilesFromDirectory(new File(directoryPath));
    }

    public static Collection<File> getFilesFromDirectory(File directory) {
        return FileUtils.listFiles(directory, new String[]{"java"}, true);
    }

    public static int getLine(SimpleName simpleName){
        return simpleName.getBegin().map(i->i.line).orElse(-1);
    }


}
