public class Point{
    private int x;
    private int y;
    public int getX = () -> x;
    public int setX = (int x) -> this.x=x;
    public int getY = () -> y;
    public int setY = (int y) -> this.y=y;
    public void switchValues(){
        int Temp;
        Temp = x;
        x = y;
        y = Temp;
    }
    public void changeValues(int v){
        int resultx,Resulty;
        resultx = x + v;
        Resulty = y + v;
        x = resultx;
        y = Resulty;
    }
}