
/*
 * THIS CLASS SOLVES FOLLOWING PROBLEMS -

 * Problem 1
 * Write a method which given two integers, returns the integer that is closest to 1000.

 * Problem 2
 * Write a method which given a string, returns a string where every character in the original is doubled. For example, given the string "xyz", return the string "xxyyzz".

 * Problem 3
 * Write a method which takes an array of integers.  The method should return true if there is a way to split the array in two so that the sum of the numbers on one side of the split equals the sum of the numbers on the other side.

 * Problem 4
 * Write a method which given a string, returns a string with an asterisk inserted between every character in the original.   Use recursion in your solution.
 */
public class AdaptiveInsightsScreening {


    /**
     * SOLUTION 1
     *
     * Returns the integer that is closest to 1000.
     *
     * @param in1 Input integer 1
     * @param in2 Input integer 2
     * @return in1 or in2 or both
     */
    public static int[] closestToThousand(int in1, int in2) {

        // Return either, if both integers are same numbers
        if (in1 == in2) {
            return new int[]{in1};
        }

        // Return the higher number when both inputs are negative
        else if (in1 < 0 && in2 < 0) {
            return in1 < in2 ? new int[]{in2} : new int[]{in1};
        }

        // Calculate distance to 1000
        else {
            // Absolute value of difference between input 1, 2 with 1000
            int abs1 = Math.abs(1000 - in1);
            int abs2 = Math.abs(1000 - in2);

            // Return both numbers if equidistant to 1000
            if (abs1 == abs2) {
                return new int[]{in1, in2};
            }
            // Return the number closest to 1000
            else {
                return abs1 > abs2 ? new int[]{in2} : new int[]{in1};
            }
        }
    }

    /**
     * SOLUTION 2
     *
     * Returns a string where every character in the original is doubled.
     * For example, given the string "xyz", return the string "xxyyzz".
     *
     * @param input   Input String to be doubled
     * @return result Doubled output String or null
     */
    public static String doubleCharacterInString(String input) {

        // Return null String if input is either null or empty
        if (input == null || input.equals("")) {
            return null;
        }

        char[] result = new char[input.length() * 2];
        int index = 0;

        // Algorithm
        for (Character c : input.toCharArray()) {
            result[index] = c;
            result[++index] = c;
            index++;
        }

        return new String(result);
    }

    /**
     * SOLUTION 3
     *
     * The method should return true if there is a way to split the array in two so that the
     * sum of the numbers on one side of the split equals the sum of the numbers on the other side.
     *
     * @param arr      Integer array to be inspected for split
     * @return boolean Return true or false depending on if array is splittable
     */
    public static boolean splitArrEqualSum(int[] arr) {
        boolean result = false;

        // Total
        int total = sum(arr);

        // Proceed if array length is greater than one in order to allow a split AND
        // If Sum of array elements is even
        if (total % 2 == 0 && arr.length > 1) {
            int leftTotal = 0;
            int rightTotal;

            // Loop
            for (int i = 0; i < arr.length - 1; i++) {

                // Increment Left sub array total
                leftTotal = leftTotal + arr[i];

                // Decrement Right sub array total
                rightTotal = total - leftTotal;

                // Return true when both sub array totals are equal
                if (leftTotal == rightTotal) {
                    result = true;
                    break;
                }
            }
            // End Loop
        }

        return result;
    }


    /**
     * Computes sum of input array elements
     *
     * @param arr  Integer array
     * @return sum Sum of integer array
     */
    private static int sum(int[] arr) {
        int sum = 0;

        // Loop
        for (int i : arr) {
            sum += i;
        }
        // End Loop

        return sum;
    }


    /**
     * SOLUTION 4
     *
     * Return true if there is a way to split the array in two so that the sum of the
     * numbers on one side of the split equals the sum of the numbers on the other side.
     *
     * @param input   Input String to be asterixed
     * @return String Asterixed output String or null
     */
    public static String insertAsterixBetweenChars(String input) {
        if (input == null || input.equals("")) {
            return null;
        }

        // Return call to recursive insert.
        return recursiveInsert(input, 0);
    }

    /**
     * Recursive function to add "*" (asterix) after every character in input String.
     *
     * @param input   Input String to be asterixed
     * @param i       Index of String where "*" is to be added
     * @return String Output asterixed String
     */
    private static String recursiveInsert(String input, int i) {
        if(i == input.length()) {
            return "";
        }

        return input.charAt(i) + "*" + recursiveInsert(input, i + 1);
    }
}
