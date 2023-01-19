package com.ensao.gi5.lint.util;

import com.ensao.gi5.lint.rules.violations.Violation;
import com.ensao.gi5.lint.wrapper.CompilationUnitWrapper;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Collection;

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
    //Creating Output Generation Methods
    public static void csvOutputFile(Collection<Violation> violations, Writer writer) throws IOException {
        String header = "FileName,Line,Description,Level\n";
        writer.write(header);
        violations.stream().forEach(violation -> {
            try {
                String data = violation.getFileName() + "," + violation.getLine() + "," + violation.getDescription()+ "," + violation.getLevel() + "\n";
                writer.write(data+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.flush();
        writer.close();

    }



    public static void jsonOutputFile(Collection<Violation> violations, Writer writer) throws IOException {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        for (Violation violation : violations) {
            json.append("{\n");
            json.append("\"FileName\": "+violation.getFileName()+"\",\n");
            json.append("\"Line\": "+violation.getLine()+"\",\n");
            json.append("\"Description\": "+violation.getDescription()+"\",\n");
            json.append("\"Level\": "+violation.getLevel()+"\",\n");
            json.append("},\n");
        }
        json.append("}\n");
        try {
            writer.write(json.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void htmlOutputFile(Collection<Violation> violations, Writer writer) throws IOException {
        for (Violation violation : violations) {
            writer.append(violation.toString()).append('\n');
        }
    }
    public static void markdownOutputFile(Collection<Violation> violations, Writer writer) throws IOException {
        for (Violation violation : violations) {
            writer.append(violation.toString()).append('\n');
        }
    }
    public static void txtOutputFile(Collection<Violation> violations, Writer writer) throws IOException {
        for (Violation violation : violations) {
            writer.append(violation.toString()).append('\n');
        }
    }
}
