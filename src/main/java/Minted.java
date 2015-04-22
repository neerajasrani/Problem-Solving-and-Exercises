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

        int [] A = {1,3,5,7,9,11,13,15,17,19};
        int [] B = {2,4,6,8,10,12,14,16,18,20,0,0,0,0,0,0,0,0,0,0};

        int [] C = {1,2,3,4,5};
        int [] D = {6,7,8,9,10,0,0,0,0,0};

/*        int [] A = {4,5,6,7,9};
        int [] B = {1,2,3,8,10,0,0,0,0,0};

        int [] A = {6,7,8,9,10};
        int [] B = {1,2,3,4,5,0,0,0,0,0};*/





        int [] result;
        //result = mergeArray(A, B, A.length);
        result = mergeArrayBetter(C, D, C.length);


        for (int i : result) {
            System.out.println(i + ",");
        }
    }

    public static int[] mergeArrayBetter(int[] a1, int[] a2, int M) {

        int i = a1.length - 1;
        int j = a2.length - 1;
        int result_pos = a1.length + a2.length - 1;
        while (i >= 0 || j >= 0) {
            if (j < 0 || (i >= 0 && a1[i] >= a2[j])) {
                a2[result_pos--] = a1[i--];
            } else {
                a2[result_pos--] = a2[j--];
            }
        }
        return a2;
    }

    public static int[] mergeArray(int[] A, int[] B, int M) {
        int al = 0;
        int ar = 0;
        int bl = 0;
        int br = 0;
        int mm = M - 1;
        int counter = 0;

        while (al<M || bl <= 2*M) {
            counter ++;
            boolean f = false;
            while(ar < M && A[ar] <= B[bl]) {
                ar++;
                f = true;
            }
            if (f)
                rightShift(A, B, al, bl, ar-al, mm);
            else
                bl++;
            mm = mm + ar - al;
            al = ar;
        }

        System.out.println(counter + "\n");
        return B;

    }

    private static void rightShift(int[] A, int[] B, int Ai, int Bi, int num, int M) {
        int counter = 0;
        for (int i = M; i >= Bi ; i--) {
            B[i+ num] = B[i];
            counter++;
        }
        System.out.println(counter);

        for(int i = Bi; i < Bi + num; i++) {
            B[i] = A[Ai];
            Ai++;
        }

    }

/*

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

*/
/*            else if(A[i] > B[j]) {

            }

            else {

            }*//*



    }

*/

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
