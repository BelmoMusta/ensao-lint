public class SimpleClass {
    public static void main(String[] args) {
        if (0 == 0 && 1 == 1 && 2 == 1) {}
        while (1 == 0 && 0 == 1 || 2 == 1) {}
        do {} while (0 == 1 || 1 == 0 && 2 == 1);
        String x = (0 == 0 || 1 == 1) || 2 == 1 ? "1": "0";
    }
}