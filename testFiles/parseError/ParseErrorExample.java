import java.util.List;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.TestClass;


public class ParseErrorExample {
    private String toto;
    public String getToto() {
        return toto;
    }
    
    public static void main(String[] args) {
        TestClass test = new TestClass();
    }

    public static <T> void T errorMethod(){}
}