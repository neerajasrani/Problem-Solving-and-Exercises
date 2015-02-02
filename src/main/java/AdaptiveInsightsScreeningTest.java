import org.junit.Assert;
import org.junit.Test;


/**
 * This class tests solutions to the following problems -
 *
 * Problem 1
 * Write a method which given two integers, returns the integer that is closest to 1000.

 * Problem 2
 * Write a method which given a string, returns a string where every character in the original is doubled. For example, given the string "xyz", return the string "xxyyzz".

 * Problem 3
 * Write a method which takes an array of integers.  The method should return true if there is a way to split the array in two so that the sum of the numbers on one side of the split equals the sum of the numbers on the other side.

 * Problem 4
 * Write a method which given a string, returns a string with an asterisk inserted between every character in the original.   Use recursion in your solution.
 */
public class AdaptiveInsightsScreeningTest {

    /**
     * TEST SOLUTION 1
     *
     * @throws Exception
     */
    @Test
    public void testClosestToThousand() throws Exception {
        int[] result;

        // Both positive, input 2 wins
        result = AdaptiveInsightsScreening.closestToThousand(10, 20);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == 20);

        // Both positive , one number greater and one less than 1000
        result = AdaptiveInsightsScreening.closestToThousand(950, 1040);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == 1040);

        // Both equidistant when positive, return both
        result = AdaptiveInsightsScreening.closestToThousand(950, 1050);
        Assert.assertTrue(result.length == 2);
        Assert.assertTrue(result[0] == 950);
        Assert.assertTrue(result[1] == 1050);

        // Both equidistant when one negative and one positive, return both
        result = AdaptiveInsightsScreening.closestToThousand(-10, 2010);
        Assert.assertTrue(result.length == 2);
        Assert.assertTrue(result[0] == -10);
        Assert.assertTrue(result[1] == 2010);

        // Both negative, input 1 wins
        result = AdaptiveInsightsScreening.closestToThousand(-10, -20);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == -10);

        // Both negative, input 2 wins
        result = AdaptiveInsightsScreening.closestToThousand(-520, -500);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == -500);

        // Both positive and equal, return either
        result = AdaptiveInsightsScreening.closestToThousand(10, 10);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == 10);

        // Both negative and equal, return either
        result = AdaptiveInsightsScreening.closestToThousand(-10, -10);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == -10);

        // Both zero and equal, return either
        result = AdaptiveInsightsScreening.closestToThousand(0, 0);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == 0);

        // One negative , One positive , positive wins
        result = AdaptiveInsightsScreening.closestToThousand(-10, 1001);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == 1001);

        // One negative , One positive , negative wins
        result = AdaptiveInsightsScreening.closestToThousand(50000, -10);
        Assert.assertTrue(result.length == 1);
        Assert.assertTrue(result[0] == -10);
    }

    /**
     * TEST SOLUTION 2
     *
     * @throws Exception
     */
    @Test
    public void testDoubleCharacterInString() throws Exception {
        // If String is null, return null
        Assert.assertEquals(AdaptiveInsightsScreening.doubleCharacterInString(null), null);

        // If String is empty, return empty
        Assert.assertEquals(AdaptiveInsightsScreening.doubleCharacterInString(""), "");

        // String of alphabets
        Assert.assertEquals(AdaptiveInsightsScreening.doubleCharacterInString("abc"), "aabbcc");

        // String of alphabets and numbers
        Assert.assertEquals(AdaptiveInsightsScreening.doubleCharacterInString("a1b2c3"), "aa11bb22cc33");

        // String of alphabets and special characters
        Assert.assertEquals(AdaptiveInsightsScreening.doubleCharacterInString("a@b$c*d/\\"), "aa@@bb$$cc**dd//\\\\");

        // String of single space
        Assert.assertEquals(AdaptiveInsightsScreening.doubleCharacterInString(" "), "  ");

        // String of two spaces
        Assert.assertEquals(AdaptiveInsightsScreening.doubleCharacterInString("  "), "    ");

        // String of single tab
        Assert.assertEquals(AdaptiveInsightsScreening.doubleCharacterInString("\t"), "\t\t");

        // String of line carriage return
        Assert.assertEquals(AdaptiveInsightsScreening.doubleCharacterInString("\n"), "\n\n");

        // String of alphabets and spaces
        Assert.assertEquals(AdaptiveInsightsScreening.doubleCharacterInString("a@b$c* d/\t\\"), "aa@@bb$$cc**  dd//\t\t\\\\");
    }

    /**
     * TEST SOLUTION 3
     *
     * @throws Exception
     */
    @Test
    public void testSplitArrEqualSum() throws Exception {
        // Empty input, not splittable
        int[] arr = {};
        Assert.assertFalse(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // Input of length 1 and +ve, not splittable
        arr = new int[]{1};
        Assert.assertFalse(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // Input of length 1 and -ve, not splittable
        arr = new int[]{-5};
        Assert.assertFalse(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // Input of length 1 and zero, not splittable
        arr = new int[]{0};
        Assert.assertFalse(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // Input of length 2, equal numbers, both positive, splittable
        arr = new int[]{100, 100};
        Assert.assertTrue(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // Input of length 2, unequal numbers, one negative, one positive, not splittable
        arr = new int[]{-101, 101};
        Assert.assertFalse(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // Input of length 2, equal numbers, both negative, splittable
        arr = new int[]{-101, -101};
        Assert.assertTrue(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // Corner case - Input of all zero's, splittable
        arr = new int[]{0, 0, 0, 0, 0, 0};
        Assert.assertTrue(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // Input of length 2, leftTotal and rightTotal is same at "-3", splittable
        arr = new int[]{-1, -5, 4, 10, -11, 49998, -50000, 9, -8, -1, 1, -2, 2};
        Assert.assertTrue(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // Even Sum, Not splittable
        arr = new int[]{3, 5, 10};
        Assert.assertFalse(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // Zero Sum, Not splittable
        arr = new int[]{3, 5, -8};
        Assert.assertFalse(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // Zero Sum, splittable
        arr = new int[]{-3, 3, -3, 3};
        Assert.assertTrue(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // Zero sum, splittable
        arr = new int[]{3, -3, -3, 3};
        Assert.assertTrue(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // Splittable at last index
        arr = new int[]{3, 5, -8, 8, 8};
        Assert.assertTrue(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // Splittable at first index
        arr = new int[]{3, 1, 1, 1};
        Assert.assertTrue(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // All positive, splittable
        arr = new int[]{3, 3, 3, 3, 12};
        Assert.assertTrue(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // All positive, not splittable
        arr = new int[]{3, 3, 3, 3, 12, 4};
        Assert.assertFalse(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // All negative, splittable
        arr = new int[]{-3, -3, -3, -3, -12};
        Assert.assertTrue(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // All negative, not splittable
        arr = new int[]{-3, -3, -3, -3, -12, -4};
        Assert.assertFalse(AdaptiveInsightsScreening.splitArrEqualSum(arr));

        // Both positive and negative, splittable at index 7.
        arr = new int[]{-1, -1, -1, -1, -1, -5, -5, -5, -5, -20, 5};
        Assert.assertTrue(AdaptiveInsightsScreening.splitArrEqualSum(arr));
    }

    /**
     * TEST SOLUTION 4
     *
     * @throws Exception
     */
    @Test
    public void testInsertAsterixBetweenChars() throws Exception {

        // Null input, return null
        Assert.assertSame(AdaptiveInsightsScreening.insertAsterixBetweenChars(null), null);

        // Empty String, return empty String
        Assert.assertTrue(AdaptiveInsightsScreening.insertAsterixBetweenChars("").equals(""));

        // Input String of length 1, alphabet
        Assert.assertTrue(AdaptiveInsightsScreening.insertAsterixBetweenChars("a").equals("a*"));

        // Input String of length 2, alphabets
        Assert.assertTrue(AdaptiveInsightsScreening.insertAsterixBetweenChars("ab").equals("a*b*"));

        // Input String of length 4, alpha numeric
        Assert.assertTrue(AdaptiveInsightsScreening.insertAsterixBetweenChars("a1b2").equals("a*1*b*2*"));

        // Input String of length 4, alpha numeric
        Assert.assertTrue(AdaptiveInsightsScreening.insertAsterixBetweenChars("a333").equals("a*3*3*3*"));

        // Input String of special character
        Assert.assertTrue(AdaptiveInsightsScreening.insertAsterixBetweenChars("\\").equals("\\*"));

        // Input String of asterix
        Assert.assertTrue(AdaptiveInsightsScreening.insertAsterixBetweenChars("****").equals("********"));
    }



}