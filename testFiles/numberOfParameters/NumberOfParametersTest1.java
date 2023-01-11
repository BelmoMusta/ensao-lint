public class NumberOfParametersTest1{
    private int p1;
    private int p2;
    private int p3;

    public  NumberOfParametersTest(int p1 ,int p2 ,int p3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    public void method1(int p1 ,int p2 ,int p3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    public void method2(int p1 ,int p2){
        this.p1 = p1;
        this.p2 = p2;
    }
}