import java.util.List;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.TestClass;
import com.github.javaparser.ast.*;
import static com.github.javaparser.ast.StaticMethod;

@Getter
@Service("test")
@Component(type=10)
public class Example {
    private String toto;
    public String getToto() {
        return toto;
    }
    
    public static void main(String[] args) {
        TestClass test = new TestClass();
    }
}