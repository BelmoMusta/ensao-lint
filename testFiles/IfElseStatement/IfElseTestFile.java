package IfElseStatement;

public class IfElseTestFile {

    public static void main(String[] args) {
        /**
         * Here i will test the IfElse rule
         * i will write a non valid if statement
         * and also a non valid else statement
         * according to my rule ( no brackets = non valid)
         * **/
        if("nonValid"="nonValid")
            System.out.println("Im a non valid if statement")
        else
            System.out.println("Im also a non valid else statement")


        /**
         * And now i will write a valid if else statement
         * according to my rule
         * **/

        if("Valid"="Valid"){
            System.out.println("HiHiHi Im a valid if statement");
        }else{
            System.out.println("HiHiHi Im also a  valid else statement");
        }
    }

}