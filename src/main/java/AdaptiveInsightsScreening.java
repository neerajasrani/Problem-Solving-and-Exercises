import org.junit.Assert;

import java.util.Arrays;

/*
Problem 1
Write a method which given two integers, returns the integer that is closest to 1000.

Problem 2
Write a method which given a string, returns a string where every character in the original is doubled. For example, given the string "xyz", return the string "xxyyzz".

Problem 3
Write a method which takes an array of integers.  The method should return true if there is a way to split the array in two so that the sum of the numbers on one side of the split equals the sum of the numbers on the other side.

Problem 4
Write a method which given a string, returns a string with an asterisk inserted between every character in the original.   Use recursion in your solution.
 */
public class AdaptiveInsightsScreening {

    public static int[] closestToThousand (int in1, int in2) {

        // If both integers are same numbers then return either.
        if(in1 == in2) {
            return new int[] {in1};
        }

        // If both negative numbers return the highest of the two
        else if (in1 < 0 && in2 < 0) {
            return in1 < in2 ? new int [] {in2} : new int [] {in1};
        }

        else {
            // Absolute value of difference between input 1 and 1000
            int abs1 = Math.abs(1000 - in1);
            int abs2 = Math.abs(1000 - in2);

            // If both numbers are equidistant to 1000 then return Input 1, as problem does not specify what to return.
            if (abs1 == abs2) {
                return new int[]{in1, in2};
            }
            // If both are positive numbers or either one is positive or negative, return the number closest to 1000
            else {
                return  abs1 >  abs2 ? new int[] {in2} : new int[] {in1};
            }
        }
    }

    public static boolean splitArrEqualSum (int[] input) {

        // Input integer array length is zero or one implies it cannot be split, return false.
        if (input.length == 0 || input.length == 1) {
            return false;
        }

        int sum = 0;

        // Compute Sum
        for (int i : input) {
            sum += i;
        }

        // If sum is even return true otherwise false
        return sum % 2 == 0;
    }

    public static String doubleCharacterInString (String s) {

        // If input is null or empty String, return the input String as is.
        if(s == null || s.equals("")) {
            return s;
        }

        char[] result = new char [s.length() * 2];
        int index = 0;

        for (Character c : s.toCharArray()) {
            result[index] = c;
            result[++index] = c;
            index++;
        }
        s = null;
        return new String(result);
    }

    public static void main (String[] args)  {

        // ------------ PROBLEM 1 --------------
        int[] result;

        // Both positive, input 2 wins
        result = closestToThousand(10, 20);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == 20);

        // Both positive , one number greater and one less than 1000
        result = closestToThousand(950, 1040);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == 1040);

        // Both equidistant when positive, return both
        result = closestToThousand(950, 1050);
        Assert.assertTrue(result.length == 2);
        Assert.assertTrue(result[0] == 950);
        Assert.assertTrue(result[1] == 1050);

        // Both equidistant when one negative and one positive, return both
        result = closestToThousand(-10, 2010);
        Assert.assertTrue(result.length == 2);
        Assert.assertTrue(result[0] == -10);
        Assert.assertTrue(result[1] == 2010);

        // Both negative, input 1 wins
        result = closestToThousand(-10, -20);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == -10);

        // Both negative, input 2 wins
        result = closestToThousand(-520, -500);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == -500);

        // Both positive and equal, return either
        result = closestToThousand(10, 10);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == 10);

        // Both negative and equal, return either
        result = closestToThousand(-10, -10);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == -10);

        // Both zero and equal, return either
        result = closestToThousand(0, 0);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == 0);

        // One negative , One positive , positive wins
        result = closestToThousand(-10, 1001);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == 1001);

        // One negative , One positive , negative wins
        result = closestToThousand(50000, -10);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == -10);

        // ------------ PROBLEM 2 --------------

        // If String is null, return null
        Assert.assertEquals(doubleCharacterInString(null), null);

        // If String is empty, return null
        Assert.assertEquals(doubleCharacterInString(""), "");

        // String of alphabets
        Assert.assertEquals(doubleCharacterInString("abc"), "aabbcc");

        // String of alphabets and numbers
        Assert.assertEquals(doubleCharacterInString("a1b2c3"), "aa11bb22cc33");

        // String of alphabets and special characters
        Assert.assertEquals(doubleCharacterInString("a@b$c*d/\\"), "aa@@bb$$cc**dd//\\\\");

        // String of single space
        Assert.assertEquals(doubleCharacterInString(" "), "  ");

        // String of two spaces
        Assert.assertEquals(doubleCharacterInString("  "), "    ");

        // String of single tab
        Assert.assertEquals(doubleCharacterInString("\t"), "\t\t");

        // String of line carriage return
        Assert.assertEquals(doubleCharacterInString("\n"), "\n\n");

        // ------------ PROBLEM 3 --------------

        // Empty input

        // Input of length 1

        // Input of length 2

    }
}
