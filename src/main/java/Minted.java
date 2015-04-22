import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by neeraj on 3/2/15.
 */
public class Minted {
    public static void main(String [] args) {


        String s = "1.; 1";

        firstRepeatedWord(s);

        int [] A = {1,3,5,7,9};
        int [] B = {2,4,6,8,10,0,0,0,0,0};

        int [] C = {2,3,5,7,9};
        int [] D = {1,4,6,8,10,0,0,0,0,0};

        int [] E = {4,3,5,7,9};
        int [] F = {1,2,6,8,10,0,0,0,0,0};

/*        int [] A = {4,5,6,7,9};
        int [] B = {1,2,3,8,10,0,0,0,0,0};

        int [] A = {6,7,8,9,10};
        int [] B = {1,2,3,4,5,0,0,0,0,0};*/






        mergeArray(A, B, A.length);
    }

    public static void mergeArray(int[] A, int[] B, int M) {
        int i = 0;
        int j = 0;


        while (j <= M) {

            while (A[i] <= B[j]) {

                // Swap the elements from 0..A[i] at B[j]
                int temp = A[i];
                A[i] = B[j];
                B[j] = temp;

                // Increment i till  A[i] > B[j]
                i++;

                int k = i;
                // Merge and sort elements from B[j] back to A[i]

                // Start comparison
                while (B[j] < A[i]) {

                }

            }

/*            else if(A[i] > B[j]) {

            }

            else {

            }*/


        }



    }

    static String firstRepeatedWord(String s) {

        if(s == null || s.equals(""))
            return "";

        String delimiters = "[\t,:;.-]";
        String[] tokens = s.split(delimiters);
        HashSet<String> words= new HashSet<String>();
        String duplicateWord = "";

        for(int i = 0; i < tokens.length; i++) {
            if(!words.add(tokens[i])) {
                duplicateWord = tokens[i];
                break;
            }
        }

        System.out.println(duplicateWord);

        return duplicateWord;
    }
}
