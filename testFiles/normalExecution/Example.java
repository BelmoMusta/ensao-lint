import java.util.List;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.TestClass;
import com.github.javaparser.ast.*;
import static com.github.javaparser.ast.StaticMethod;

@Getter
@Service("test")
@Component(type=10)
public class example {
    private String Toto;
    
    final int VAR_CONST=0;
    
    public String getToto() {
        return toto;
    }
    
    public void oprandTest(int a, int b, int c) {
    	if(a && b && c && a) {
    		System.out.println("helloe");
    	}
    }
    
    public static void main(String[] args) {
        TestClass test = new TestClass();
    }
}

public enum testing {
    BLOCK_ER,
    HIGH_EST,
    high,
    ;
}