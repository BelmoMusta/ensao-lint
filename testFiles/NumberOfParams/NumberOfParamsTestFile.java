public class NumberOfParamsTestFile {

    private int var1,var2,var3;

    /**
     * To test the rule i will write 3 constructor
     * the first one is invalid bcs its conatins more than 2 params
     * The second and the third one are valids
     * **/

    public NumberOfParamsTestFile(int var1, int var2, int var3) {
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
    }

    public NumberOfParamsTestFile(int var1, int var2) {
        this.var1 = var1;
        this.var2 = var2;
    }

    public NumberOfParamsTestFile(int var1) {
        this.var1 = var1;
    }

    /**
     * And now to test the methods
     * i will write one valid and one non valid
     * the non valid method will contain more than 2 params
     *
     * **/

    public void mtdValid(){
        System.out.println("Im valid method i have no params");
    }

    public void mtdNonValid(int par1, long par2, float par3){
        System.out.println("Im a non valid method bcz i have 3 params");
    }
}