import java.util.List;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.TestClass;
import com.github.javaparser.ast.*;
import static com.github.javaparser.ast.StaticMethod;

@Getter
@Service("test")
@Component(type=10)
public class Example {
     String toto;

    public String getToto() {
        return toto;
    }
    public void IfElseStatementAndMultipleOperands(String a , String b ){
        if((a && b && a) || b) {
            System.out.println("true")
        };
    }
    public void NumberLinesLessThanThirty(){
        System.out.println("Oussama");
        System.out.println("Mustapha");
        System.out.println("Oussama");
        System.out.println("Mustapha");
        System.out.println("Oussama");
        System.out.println("Mustapha");
        System.out.println("Oussama");
        System.out.println("Mustapha");
        System.out.println("Oussama");
        System.out.println("Mustapha");
        System.out.println("Oussama");
        System.out.println("Mustapha");
        System.out.println("Oussama");
        System.out.println("Mustapha");
        System.out.println("Oussama");
        System.out.println("Mustapha");
        System.out.println("Oussama");
        System.out.println("Mustapha");
        System.out.println("Oussama");
        System.out.println("Mustapha");
        System.out.println("Oussama");
        System.out.println("Mustapha");
        System.out.println("Oussama");
        System.out.println("Mustapha");
        System.out.println("Oussama");
        System.out.println("Mustapha");
        System.out.println("Oussama");
        System.out.println("Mustapha");
        System.out.println("Oussama");
        System.out.println("Mustapha");
    }

    
    public static void main(String[] args) {
        TestClass test = new TestClass();
    }
}