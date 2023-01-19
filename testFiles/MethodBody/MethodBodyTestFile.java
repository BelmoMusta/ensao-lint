public class MethodBodyTest {

    /**
     * To test our rule i will provide a methode
     * that contain more than 30 line
     * **/
    void methodeNonValid() {
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("Tcha Tcha Tcha");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("Tcha Tcha Tcha");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("Tcha Tcha Tcha");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("Tcha Tcha Tcha");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("Tcha Tcha Tcha");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("Tcha Tcha Tcha");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("Tcha Tcha Tcha");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("Tcha Tcha Tcha");
    }

    /**
     * Now i will write a valid method that contain
     * few lines so according to my rule its valid
     * **/
    void methodValid() {
        System.out.println("I'm a tiny mini methode");
    }
}