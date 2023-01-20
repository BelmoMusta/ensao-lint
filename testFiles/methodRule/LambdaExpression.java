import java.util.function.Function;

public class LambdaExpression {
	
    public static <T> T mergeThings(T a, T b, BiFunction<T, T, T> merger) {
        return merger.apply(a, b);
    }
    
    public static String appendStrings(String a, String b) {
        return a + b;
    }
    
    public static void main(String[] args) {
    	
        String result = LambdaExpression.mergeThings("Hello ", "World!", (a, b) -> a + b);
        System.out.println(result);
        
    }
}