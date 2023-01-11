import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.utils.SourceRoot;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public  static  void main(String[] args) throws IOException {
        Path dirPath =  Paths.get("C:\\JavaParserTest\\src\\main\\resources\\src\\");
        SourceRoot sourceRoot = new SourceRoot(dirPath);
        sourceRoot.tryToParse();
        CompilationUnit cu = sourceRoot.parse("", dirPath.toString()+"\\JavaFile.java");

        cu.accept(new ModifierVisitor<Void>() {
            @Override
            public Visitable visit(ClassOrInterfaceDeclaration n, Void arg) {
                RemoveOtherClassesFromSameFile(n, cu);
                return super.visit(n, arg);
            }
        }, null);

        sourceRoot.saveAll(Paths.get(dirPath.toString()+"\\JavaFile.java"));
    }

    private static ClassOrInterfaceDeclaration RemoveOtherClassesFromSameFile(ClassOrInterfaceDeclaration n, CompilationUnit cu) {
        List<ClassOrInterfaceDeclaration> internalClasses = (
                n.getMembers().stream().filter(m -> m instanceof ClassOrInterfaceDeclaration)
                        .map(m -> (ClassOrInterfaceDeclaration) m).collect(Collectors.toList()));
        for (ClassOrInterfaceDeclaration c : internalClasses){
            n.remove(c);
        }
        return n;
    }

}