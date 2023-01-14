public class SimpleClass {
    public static void main(String[] args) {
        if (0 == 0 && 1 == 1) {}
        while (1 == 0 || 0 == 1) {}
        do {} while (0 == 1 && 1 == 0);
        String x = 0 == 0 && 1 == 1 ? "1": "0";
    }
}