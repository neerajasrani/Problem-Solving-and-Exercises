import java.util.ArrayList;
import java.util.List;

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
 * Explanation - The String does not contain 'u'
 *
 */
public class LongestMagicalSubsequenceOfVowels {

    public static void main(String[] args) {

        String s = args[0];
        char [] c = s.toCharArray();
        List<Integer> aPositions = new ArrayList<>();
        int maxSubsequenceLength = 0;
        int subSequenceCount;

        // Fill aPositions list with indices having an "a"
        for (int i = 0; i < s.length(); i++) {
            if (c[i] == 'a') {
                aPositions.add(i);
            }
        }
        for (int i: aPositions) {
            // Call findMaxSubsequence for all aPositions found in input String
            subSequenceCount = findMaxSubsequence(c, i);
            if (subSequenceCount > maxSubsequenceLength) {
                maxSubsequenceLength = subSequenceCount;
            }
        }
        System.out.println(maxSubsequenceLength);
    }

    private static int findMaxSubsequence(char[] c, int pos) {
        int subSequenceCount = 0;
        char[] vowels = new char[] {'a','e','i','o','u', '\0'};
        boolean isNextVowelFound = true;

        for (int i = pos; i < c.length; i++) {
            for (int v = 0 ; v < vowels.length - 1; v++) {
                boolean nextVowelFound = false;
                int j;
                for (j = i; j < c.length; j++) {
                    if (isNextVowelFound) {
                        if (vowels[v] == 'u') {
                            if (c[j] == 'u') {
                                nextVowelFound = true;
                                subSequenceCount++;
                            }
                        } else {
                            if (c[j] == vowels[v]) subSequenceCount++;
                            if (c[j] == vowels[v + 1]) {
                                nextVowelFound = true;
                                break;
                            }
                        }
                    } else {
                        return 0;
                    }
                }
                i = j;
                isNextVowelFound = nextVowelFound;
            }
        }
        return subSequenceCount;
    }
}
