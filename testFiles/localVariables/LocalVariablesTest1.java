public class Point{
    private int x;
    private int y;
    public int getX(){return x;}
    public void setX(int x) {this.x=x;}
    public int getY(){return y;}
    public void setY(int x) {this.y=y;}
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