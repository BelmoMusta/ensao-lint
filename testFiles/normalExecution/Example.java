import com.github.javaparser.ast.TestClass;
import com.github.javaparser.ast.*;
import static com.github.javaparser.ast.StaticMethod;

@Getter
@Service("test")
@Component(type=10)
interface Addable{
    int add(int a,int b);
}

public class Example {
      String toto;
    
    public String getToto() {
        return toto;
    }

    private void display(String a , String b,String c){
        System.out.println(a + b + c);
        if(true) {
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
    }

    public void printHello(){
        System.out.println("Hello world");
    }
    
    public static void main(String[] args) {
        TestClass test = new TestClass();
        Addable ad1=(a,b)->(a+b);
        System.out.println(ad1.add(10,20));
    }
}