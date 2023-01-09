package Statement;

public class StatementTest {
    private int x;

    public StatementTest(int x) {
        this.x = x;
    }

    public boolean isValidX(){
        if(this.x >0 || this.x<100 || this.x != 50){
            return true;
        }else{
            return false;
        }
    }

    public void testonX(Predicate<Integer> predicate){
        predicate.test(this.x);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public static void main(String[] args){

        try{
            int operation = 10/this.x;
        }catch (ArithmeticException e){

        }


        if(this.x == 5)
            System.out.println("What a lucky day");

        Predicate<Integer> notTen = new Predicate<Integer>() {
            public boolean test(Integer integer) {
                return this.x==10;
            }
        };

        System.out.println(this.testonX(i -> i==10));

    }
}

interface Predicate<T>{
    boolean test(T t);
}