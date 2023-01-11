public class AnonymousTest{
    public Runnable runnable = new Runnable() {
        public void run() {
            System.out.println("Hello from anonymous inner class!");
        }
    };
}