

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
 * Explanation - The String does not contain U
 *
 */
public class LongestMagicalSubsequenceOfVowels {

    public static void main(String[] args) {

        String s = args[0];

        // Stores a positions
        char [] c = s.toCharArray();
        List<Integer> aPositions = new ArrayList<Integer>();
        int maxSubsequence = 0;


        // Fill aPositions list with indices having an "a"
        for (int i = 0; i < s.length(); i++) {
            if (c[i] == 'a') {
                aPositions.add(i);
            }
        }

        int subSequenceCount = 0;
        // Call findMaxSubsequence for all aPositions found in input String
        for (int i: aPositions) {
            subSequenceCount = findMaxSubsequence(c, i);
            if (subSequenceCount > maxSubsequence) {
                maxSubsequence = subSequenceCount;
            }
        }

        System.out.println(maxSubsequence);
    }

    private static int findMaxSubsequence(char[] c, int pos) {
        char[] vowels = new char[] {'a','e','i','o','u'};
        Result result = new Result();

        for (int i = pos; i < c.length; i++) {
            for (int v = 0 ; v < vowels.length - 1; v++) {
                if (!result.isVowelNotFound()) {
                    countSubSequence(c, vowels[v], vowels[v + 1], i, result);
                    i = result.getPos();
                }
                else {
                    return 0;
                }
            }
        }
        return result.isVowelNotFound() ?  0  : result.getSubSequenceCount();
    }

    private static void countSubSequence(char[] c, char vowel1, char vowel2, int pos, Result result) {
        int subSequenceCount = 0;
        boolean lastVowelFound = false;

        while (pos < c.length && c[pos] != vowel2) {
            if (c[pos] == vowel1) {
                subSequenceCount++;
            }
            pos++;
        }
        if (pos >= c.length || c[pos] != vowel2){
            result.setVowelNotFound(true);
        }
        if (vowel2 == 'u') {
            while (pos < c.length) {
                if (c[pos] == 'u') {
                    subSequenceCount++;
                    lastVowelFound = true;
                }
                pos++;
            }
            // The required vowel if not found , setVowelNotFound to true and return 0
            if (!lastVowelFound) {
                result.setVowelNotFound(true);
            }
        }

        result.setPos(pos);
        result.setSubSequenceCount(result.getSubSequenceCount() + subSequenceCount);
    }

    static class Result {
        private int subSequenceCount;
        private int pos;
        private boolean vowelNotFound;

        public boolean isVowelNotFound() {
            return vowelNotFound;
        }

        public void setVowelNotFound(boolean vowelNotFound) {
            this.vowelNotFound = vowelNotFound;
        }

        public int getSubSequenceCount() {
            return subSequenceCount;
        }

        public void setSubSequenceCount(int subSequenceCount) {
            this.subSequenceCount = subSequenceCount;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }
    }
}
