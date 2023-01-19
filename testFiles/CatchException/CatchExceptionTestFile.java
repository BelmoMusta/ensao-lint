package CatchException;

public class CatchExceptionTestFile {
    public static void main(String[] args) {
            /**
             * Here i will give code that will throw an exception
             * without logging the exception in the catch block
             * NB:the code inside the try block may throw an IllegalArgumentException if
             * the length of the command line arguments is zero.
             * **/
            try{
                if (args.length == 0) {
                    throw new IllegalArgumentException("Missing command line arguments.");
                }
            }catch (IllegalArgumentException e){

            }

         /**
         * Here i will give code that will throw an exception
         * But in the catch block i will log the exception
          * so this code is the valid according to my rule
         * **/
        try{
            if (args.length == 0) {
                throw new IllegalArgumentException("Missing command line arguments.");
            }
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        }

}