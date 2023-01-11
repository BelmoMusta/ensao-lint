import java.util.Arrays;
import java.util.List;

public class LambdaTest {

        private List<String> list = Arrays.asList("A", "B", "C");

        public void method() {
            list.forEach(s -> System.out.println(s));
        }
}