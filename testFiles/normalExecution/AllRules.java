public enum color {
    red, green, blue
}

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

public class ClassAttributesStartWithLowerCaseTest {
    private String AttributeName;
    final private int attributeName2;
    public ClassAttributesStartWithLowerCaseTest() {
    }
}

class ClassAttributeVisibilityTest{
    int coord;
    private int absc;
}

public class LocalVariablesStartWithLowerCaseTest {
    public void testMethod() {
        String VariableName;
    }
}

public class MethodAndConstructorParamsTest{
    private int x;
    private int y;
    private int z;

    public MethodAndConstructorParamsTest(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public void affect(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

}