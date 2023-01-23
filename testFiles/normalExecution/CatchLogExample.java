public class CatchLogExample {

    public void test() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            // log statement is missing
        }
    }
}
