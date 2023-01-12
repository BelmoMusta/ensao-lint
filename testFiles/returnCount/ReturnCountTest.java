public class ReturnCountTest {
    public int method1() {
        if (true) {
            return 1;
        } else {
            return 2;
        }
    }

    public int method2() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                return i;
            }
        }
        return -1;
    }

    public void method3() {
        System.out.println("Hello");
    }
}
