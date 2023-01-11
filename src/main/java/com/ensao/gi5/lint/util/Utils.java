package com.ensao.gi5.lint.util;

import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.ensao.gi5.lint.wrapper.TypeWrapper;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithRange;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {
     Utils() {
        throw new IllegalStateException("not to be instantiated");
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

    public static String getTypeName(TypeDeclaration<?> typeDeclaration) {
         if (typeDeclaration.isAnnotationDeclaration()) {
             return "Annotation";
         } else if (typeDeclaration.isEnumDeclaration()) {
             return "Enum";
         } else {
             ClassOrInterfaceDeclaration classOrInterfaceDeclaration = typeDeclaration.asClassOrInterfaceDeclaration();
             if (classOrInterfaceDeclaration.isInterface()) {
                 return "Interface";
             } else if (classOrInterfaceDeclaration.isInnerClass()) {
                 return "Inner Class";
             } else {
                 return "Class";
             }
         }
    }

    public static int getLine(NodeWithRange<?> node) {
         return node.getBegin().map(p -> p.line).orElse(-1);
    }

    public static String getTypeNameFromNodes(Node node) {
         return ((TypeDeclaration<?>) node.getParentNode().get()).getNameAsString();
    }

    public static List<TypeWrapper> checkIfNamesDontFollowsRule(List<TypeWrapper> typeNames) {
        String regex = "^[A-Z][a-z0-9]*(?:[A-Z][a-z0-9]*)*(?:[A-Z]?)$";
        return typeNames.stream()
                .filter(type -> !Pattern.matches(regex, type.getTypeName()))
                .collect(Collectors.toList());
    }
}
