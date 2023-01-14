public class Rule006Test {
    public void test() {
        boolean a = true;
        boolean b = true;
        boolean c = false;

        boolean result;

        if (a && b || c) {
            result = a;
        }
        if (a || b || c) {
            result = b;
        }
    }
}