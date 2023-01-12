public class CatchLogExceptionsTest {
    public void testMethod1() {
        try {
            // some code that may throw an exception
        } catch (Exception e) {
            // missing log statement
        }
    }

    public void testMethod2() {
        try {
            // some code that may throw an exception
        } catch (Exception e) {
            Logger.getLogger(CatchLogExceptionsTest.class.getName()).log(Level.LOW, null, e);
        }
    }
}
