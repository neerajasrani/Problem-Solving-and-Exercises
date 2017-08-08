/**
 * Created by nickamac on 8/3/17.
 *
 * Probalem found when screening for Astro company
 *
 * Return length of the longest subsequence containing a magical subsequence of vowels "a", "e", "i", "o", "u"
 * You will be given a input String containing letter from the given vowels
 *
 * e.g. aeiaaioooaauuaeiou
 * O/p - 10
 * Explanation , this is the output - aeiiooouuu
 *
 * aeeioo
 * O/p - 0
 * Explanation - The String does not contain U
 *
 */
public class LongestMagicalSubsequenceOfVowels {

    public static void main(String[] args) {

        String s = args[0];

        // Stores a positions
        char [] c = s.toCharArray();
        int [] aPositions = new int[s.length()];
        int aPosCtr = 0;
        int maxSubsequence = 0;

        // Fill aPosCtr array
        for (int i = 0; i < s.length(); i++) {
            if (c[i] == 'a') {
                aPositions[aPosCtr] = i;
                aPosCtr++;
            }
        }

        int subSequenceCount = 0;
        // Call findMaxSubsequence for all aPositions found in input String
            for (int i = 0; i < aPositions.length; i++) {
            subSequenceCount = findMaxSubsequence(c, aPositions[i]);
            if (subSequenceCount > maxSubsequence) {
                maxSubsequence = subSequenceCount;
            }
        }

        System.out.println(maxSubsequence);
    }

    private static int findMaxSubsequence(char[] c, int pos) {
        boolean foundAllVowels = false;
        int subSequenceCount = 0;

        for (int i = pos; i < c.length; i++) {
            while (c[i] != 'e') {
                if (c[i] == 'a') {
                    subSequenceCount++;
                }
                i++;
            }
            if (c[i] != 'e') {
                return 0;
            }
            while (c[i] != 'i') {
                if (c[i] == 'e') {
                    subSequenceCount++;
                }
                i++;
            }
            if (c[i] != 'i') {
                return 0;
            }
            while (c[i] != 'o') {
                if (c[i] == 'i') {
                    subSequenceCount++;
                }
                i++;
            }
            if (c[i] != 'o') {
                return 0;
            }
            while (c[i] != 'u') {
                if (c[i] == 'o') {
                    subSequenceCount++;
                }
                i++;
            }
            if (c[i] == 'u') {
                subSequenceCount++;
            }
        }

        return subSequenceCount;
    }
}
