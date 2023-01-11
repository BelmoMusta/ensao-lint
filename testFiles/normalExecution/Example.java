import java.util.List;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.TestClass;
import com.github.javaparser.ast.*;
import static com.github.javaparser.ast.StaticMethod;

@Getter
@Service("test")
@Component(type=10)
public class example {
    String Toto = "";
	private class Test_ {
        private String Xd;
        public static boolean getToto() {
            return 5 == 9 || 6 == 9 && 8 == 5;
        }
    }
    
    public String getToto() {
    }
    
    public static void main(String[] args) {
        TestClass test = new TestClass();
    }
}

public class Test2 {}
public interface interface1 {}
public @interface annotation1 {}
public enum enum1 {}