package LocalVariableName;

public class LocalVariableNameTestFile {

    /**
     * To test the LocalVaribaleName i will add some localVariables
     * to some methods and i will not put the first letter in lowerCase
     * so according to my rule this is an error
    **/
    public void methode1(){

        int NonValidLocalVaribale1;

        //Some code
    }
    public void methode2(){
        int NONVALIDVARIABLE;

        //Some code

    }


    /**
     * Here is some valid code to pass the test
     * the local vars should start with lowercase
     **/
    public void methodeValide(){
        int validLocalVariable;

        //Some code
    }
}
