public class LambdaExpressionTestFile {

    /**
     * To test the rule i will add  Lambda expression
     * NB: ()->()
     * this is the forme of a lambda expression
     */

    private List<String> listOfStrings = Arrays.asList("TEST1", "TEST2", "TEST3");

    public void method() {
        list.forEach(s -> System.out.println(s.length()));
    }
}