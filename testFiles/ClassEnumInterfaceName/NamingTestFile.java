package ClassEnumInterfaceName;


/**
 * Here i will give bad declarations to test my rule
 * **/

//this interface does not start with capital letter
public interface testNamingInterface {
}

//This class start with capital letter but it contain _
public class Test_NamingClass {

}

//this Enum does not start with capital letter and contain _
public enum test_Enum {
}
/**
 * Here i will give the perfect declarations according to my rule
 * **/
public interface TestInterface {
}

public class TestClass {

}
public enum TestEnum {
}
