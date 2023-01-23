public class BooleanExpressionTest {
    public void test() {
        boolean a = true;
        boolean b = false;
        boolean c = true;
        boolean d = false;

        // Violation: more than 2 operands
        boolean result = a && b || c && d;

        // Violation: more than 2 operands
        boolean result2 = a && b || c && d || a && b;
    }
}
