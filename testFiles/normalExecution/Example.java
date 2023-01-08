import java.util.List;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.TestClass;
import com.github.javaparser.ast.*;
import java.util.function.*;
import static com.github.javaparser.ast.StaticMethod;

@Getter
@Service("test")
@Component(type=10)
public class Example {
    public static int constant;
    public Example(int i, int j){

    }
    private String toto;
    
    public String getToto(){
        return this.toto;
    }

    public void setToto(String toto) {
        this.toto = toto;
    }
    
    public static void main(String[] args) {
        Integer x = new Integer(10);
        TestClass Test = new TestClass();
        if(x==10 || x== 2) {
            System.out.println(x);
        }

    }
}
