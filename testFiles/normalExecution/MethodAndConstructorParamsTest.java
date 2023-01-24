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