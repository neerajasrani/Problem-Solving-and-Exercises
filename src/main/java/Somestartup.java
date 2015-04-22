import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by neeraj on 4/21/15.
 */
public class Somestartup {
/*    Programming Questions
    1. Write a function that given a string with a sentence returns a string with words reversed in the sentence. Don’t worry about punctuation.

    My name is Neeraj

    Neeraj is name My*/


    public static String reverseSentence(String input) {

        if (input == null) {
            return null;
        }

        String[] words = input.split(" ");

        StringBuilder result = new StringBuilder();

        for (int i=words.length - 1 ; i >=0; i--) {
            result.append(words[i] + " ");
        }

        return result.substring(0, result.length() - 1);

    }

    public static void main(String[] args) {
        System.out.println(reverseSentence("Neeraj   is    my    name"));
        int[] result = findTwoNumbersThatSum(new int[]{10,20,-30, -70, -100, 7000, 10}, 6970);
        if(result.length == 2)
            System.out.println(result[0] + " , " + result[1]);
        else
            System.out.println("None");

    }





    // This is the text editor interface.
// Anything you type or change here will be seen by the other person in real time.

//input : unsorted array and a number
//output : two numbers from the array, whose sum == number

//    sum = 6

//  1,2,3,4,5,6,7

    public static int[] findTwoNumbersThatSum(int input[], int sum) {

        Arrays.sort(input);

        int li = 0;
        int ri = input.length - 1;

        while (li <= ri) {


            if (input[li] + input[ri] == sum) {
                return new int[] {input[li], input[ri]};
            }

            if (input[li] + input[ri] < sum) {
                li++;
            } else {
                ri--;
            }

        }

        return new int[]{};




    }







/*    2. Write a function to Reverse a Binary Tree (i.e. create a mirror image)

    Input:


    Result


    O(n)*/

/*    public void mirrorTree(Node root) {

        if (root.left ==null && root.right ) {
            return;
        }


        Node left = root.left;
        Node right = node.right;

        int temp = left.data;
        left.data = right.data;
        right.data = temp;


        mirrorTree(root.left);
        mirrorTree(root.right);


    }*/
}
