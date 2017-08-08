import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by neeraj on 1/2/17.
 * From meetup held Dec 31, 2016. Yes, just before New Years eve I was studying
 * String problems are common screening questions and are commonly asked in telephone
 interviews using a shared document website like CoderPad. Since systems like these don't
 offer autocomplete and suggestions, it's a good idea to know a handful of different string
 methods available for your language such as how to calculate string length, how to access a
 character at an index and how to extract a substring from a regular string.
 */
public class StringInterviewQuestions {

    public static void main(String[] args) {

//        System.out.println(StrUtils.reverse("abc"));
//        System.out.println(StrUtils.reverse(""));
//        System.out.println(StrUtils.reverse(null));
//        System.out.println(StrUtils.reverse("ab"));
//        System.out.println(StrUtils.reverse("dregfkl mn"));
//        System.out.println(StrUtils.reverse("a"));
//
//        System.out.println(StrUtils.reverse("dregfkl mn",0,3));
//        System.out.println(StrUtils.reverse("abc",1,2));
//
//        System.out.println(StrUtils.reverse("dog cat",3,5)); // -> “dog tac” --> should be "dogac t"
//        System.out.println(StrUtils.reverse("abc",0,2)); // -> “cba”
//        System.out.println(StrUtils.reverse("abc",2,-2)); // -> “abc”
//        System.out.println(StrUtils.reverse("",0,0)); // -> “”
//        System.out.println(StrUtils.reverse(null,-3,0)); // -> null
//        System.out.println(StrUtils.reverse("abcde",-10,30)); // -> “edcba”
//
//        System.out.println(StrUtils.reverseWords("the quick      brown      fox jumped  over the lazy dog"));
//        System.out.println(StrUtils.reverseWords("abc"));
//        System.out.println(StrUtils.reverseWords("abc def ghi jkl"));
//        System.out.println(StrUtils.reverseWords(null));
//        System.out.println(StrUtils.reverseWords("a"));
//        System.out.println(StrUtils.reverseWords("                        "));
//
//        System.out.println(StrUtils.isPalindrome(""));
//        System.out.println(StrUtils.isPalindrome("a"));
//        System.out.println(StrUtils.isPalindrome("ab"));
//        System.out.println(StrUtils.isPalindrome("aba"));
//        System.out.println(StrUtils.isPalindrome("abab"));
//        System.out.println(StrUtils.isPalindrome("ababc"));
//        System.out.println(StrUtils.isPalindrome("abba"));
//
//        System.out.println(StrUtils.findSubstring("d", "bacd"));
//        System.out.println(StrUtils.findSubstring("a", "bacd"));
//        System.out.println(StrUtils.findSubstring("e", "bacd"));
//        System.out.println(StrUtils.findSubstring(" ", "bacd"));
//        System.out.println(StrUtils.findSubstring("bacd", "bacd"));
//        System.out.println(StrUtils.findSubstring("acd", "bacd"));
//        System.out.println(StrUtils.findSubstring("acb", "bacd"));
//        System.out.println(StrUtils.findSubstring("bacde", "bacdbacde"));
//        System.out.println(StrUtils.findSubstring("bacde", "bacbabacdefghbacde"));
//        System.out.println(StrUtils.findSubstring("d", "d"));
//        System.out.println(StrUtils.findSubstring("", ""));
//
//        System.out.println(StrUtils.findSmallestSubstring("abyzhxdexzyr", "yzx"));
//        System.out.println(StrUtils.findSmallestSubstring("abyzyxdexzr", "yzx"));

        System.out.println(StrUtils.findMinStep("WWRRBBWW", "YRBGB"));
    }
}

/**
 * Created by neeraj on 1/2/17.
 * Create a class that will hold all the string utilities we'll be writing.
 */
class StrUtils extends StringInterviewQuestions {

    private static HashMap<Integer, Integer> word_index = new HashMap<>(10);
    /**
     * Write a method that takes in a String and returns a new String with the letters in reverse
     * order. The original string passed in remains in tact, and the returned string a newly
     * created string.
     *n
     * @param str String to be reversed
     * @return Reversed String
     */
    static String reverse(String str) {
        if (str==null) {
            return null;
        }
        return reverse(str,0,str.length()-1);
    }


    /**
     *
     * Write a method that takes in a String and returns a reversed substring within original
     * string. Clamp the startIndex and endIndex within the range of the string.
     *
     * @param str String's substring to be reversed
     * @param startIndex Start index of String reversal
     * @param endIndex End index of String reversal
     * @return String with reversed substring
     */
    private static String reverse(String str, int startIndex, int endIndex, boolean captureWordIndex) {
        if (str==null) {
            return null;
        }
        if (startIndex > str.length()-1 || endIndex < 0 || startIndex > endIndex) {
            return str;
        }
        if (startIndex < 0 ) {
            startIndex=0;
        }
        if (endIndex > str.length()-1) {
            endIndex=str.length()-1;
        }
        int lptr=startIndex;
        int llptr=startIndex;
        int rptr=endIndex;
        int rrptr=endIndex;
        char[] str_char_arr = str.toCharArray();
        while (lptr<=rptr) {
            if (captureWordIndex) {
                if (str_char_arr[lptr] == ' ') {
                    word_index.put(llptr, lptr-1);
                    llptr = lptr+1;
                }
                if (str_char_arr[rptr] == ' ') {
                    word_index.put(rptr+1, rrptr);
                    rrptr = rptr-1;
                }
            }
            char tmp=str_char_arr[lptr];
            str_char_arr[lptr] = str_char_arr[rptr];
            str_char_arr[rptr] = tmp;
            lptr+=1;
            rptr-=1;
        }
        if (captureWordIndex && llptr < rrptr) {
            word_index.put(llptr,rrptr);
        }

        return new String(str_char_arr);
    }

    private static String reverse(String str, int startIndex, int endIndex) {
        if (str==null) {
            return null;
        }
        return reverse(str, startIndex, endIndex, false);
    }

        /**
         * Write a method that takes in a sentence with words separated by spaces. Ignore punctuation.
         * Return a new sentence with the word order reverse. For example:
         * “the quick brown fox jumped over the lazy dog” ->
         * “dog lazy the over jumped fox brown quick the”
         * Algorithm: -
         * Reverse the entire string: “god yzal eht revo depmuj xof nworb kciuq eht”–
         * Reverse each word: “dog lazy the over jumped fox brown quick the”
         *
         *
         * @param sentence String with 1 or more words or null
         * @return String with reversed words
         */
    static String reverseWords(String sentence) {
        if (sentence==null) {
            return null;
        }

        // case when there is only one word followed by one space.
        // case when there is are 2 spaces in between words
        // case when there are multiple spaces in between words
        // case when there are only spaces in the sentence

        String result=reverse(sentence, 0, sentence.length()-1, true);

        // special case when there are no spaces in the sentences, which means only one word in the sentence
        if (word_index.isEmpty()) {
            return sentence;
        }

        for(Map.Entry<Integer,Integer> entry: word_index.entrySet()) {
            result=reverse(result, sentence.length()-1-entry.getValue(), sentence.length()-1-entry.getKey());
        }
        word_index = new HashMap<>(10);
        return result;
    }

    /**
     * Write a method to determine whether a string is a palindrome, that means that it reads the
     * same way forwards and backwards.
     *
     * @param str Input String
     * @return true if Palindrome, False otherwise
     *
     * // Diego, 313.
     * 91,2261273891 (claims department from oriental)
     * 135772, Poonam
     *
     */
    static boolean isPalindrome(String str) {
        if (str==null) {
            return false;
        }
        int lptr=0;
        int rptr=str.length()-1;
        while (lptr<=rptr) {
            if (str.charAt(lptr) != str.charAt(rptr)) {
                return false;
            }
            lptr++;
            rptr--;
        }
        return true;
    }

    /**
     * Write a method to that finds substring in a longer string. For example, given:
     * “Twinkle, twinkle, little star”
     * 01234567890123456789012345678
     *           1         2
     * return index for “target” in “body”. If unfound, return -1.
     * @param target Target string to be searched in body string
     * @param body Main input String
     * @return int -1 if not found, otherwise start index of substring
     */
    static int findSubstring(String target, String body) {

        // start with a pointer looking for first character
        // keep track of index starting from first let from "target" in body"
        // complexity - O(b*t)
        if (body==null || target==null) {
            return -1;
        }
        int ptr=0;
        int next_ptr=-1;                int new_ptr = ptr;
        int target_ptr=0;
        boolean is_substr_found = true;
        boolean next_ptr_recorded= false;
        while (ptr<=body.length()-1) {
            if (body.length() - ptr < target.length()) { //optimization
                return -1;
            }
            if(body.charAt(ptr) == target.charAt(0)){
                new_ptr = ptr;
                target_ptr=0;
                is_substr_found = true;
                next_ptr_recorded= false; //needed to record ONLY first occurrence of target string in body
                while(target_ptr<=target.length()-1) {
                    if (body.charAt(new_ptr) != target.charAt(target_ptr)) {
                        is_substr_found=false;
                        break;
                    }
                    else {
                        if (body.charAt(new_ptr) == target.charAt(0) && !next_ptr_recorded) {
                            next_ptr = new_ptr;
                            next_ptr_recorded=true;
                        }
                        new_ptr++;
                        target_ptr++;
                    }
                }
                if (is_substr_found) {
                    return ptr;
                }
            }
            ptr=next_ptr_recorded && body.length()-next_ptr < target.length()?next_ptr:ptr+1; //optimization
        }
        return -1;
    }

    static String findSmallestSubstring(String str, String arr) {
        int t = 0;
        String result = null;
        int uniqueCounter = 0;
        Map<Character,Integer> countMap = new HashMap<>();
        // initialize countMap:
        for (int i=0; i <arr.length(); i++)
            countMap.put(arr.charAt(i), 0);
            // scan str
            for (int h=0;h<str.length(); h++) {
            // handle the new head
            char head = str.charAt(h);
            if (!countMap.containsKey(head))
                continue;
            int headCount = countMap.get(head);
            if (headCount == 0) {
                uniqueCounter = uniqueCounter + 1;
            }
            countMap.put(head, headCount + 1);
            //push tail forward
            while (uniqueCounter == arr.length()) {
                int tempLength = h - t + 1;
                if (tempLength == arr.length()) {
                    return str.substring(t, h);
                }
                if (result==null || tempLength <result.length()) {
                    result = str.substring(t, h);
                }
                char tail = str.charAt(t);
                if (countMap.containsKey(tail)) {
                    int tailCount = countMap.get(tail) - 1;
                    if (tailCount == 0) {
                        uniqueCounter = uniqueCounter - 1;
                    }
                    countMap.put(tail, tailCount);
                }
                t = t + 1;
            }
        }
        return result;
    }

    /**
     *
     * Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). You also have several balls in your hand.

     Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls can be removed.

     Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.

     Examples:

     Input: "WRRBBW", "RB"
     Output: -1
     Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW

     Input: "WWRRBBWW", "WRBRW"
     Output: 2
     Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty

     Input:"G", "GGGGG"
     Output: 2
     Explanation: G -> G[G] -> GG[G] -> empty

     Input: "RBYYBBRRB", "YRBGB"
     Output: 3
     Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty

     Note:
     You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
     The number of balls on the table won't exceed 20, and the string represents these balls is called "board" in the input.
     The number of balls in your hand won't exceed 5, and the string represents these balls is called "hand" in the input.
     Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.
     *
     * @param board
     * @param hand
     * @return
     */
    static int findMinStep(String board, String hand) {
        List<Character> boardList = new ArrayList<Character>();
        for (char c : board.toCharArray()) {
            boardList.add(c);
        }
        Map<Character,Integer> handMap = new HashMap<>();
        handMap.put('R',0);
        handMap.put('Y',0);
        handMap.put('B',0);
        handMap.put('G',0);
        handMap.put('W',0);
        for (char h : hand.toCharArray()) {
            handMap.put(h, handMap.get(h) + 1);
        }
        return find(boardList, handMap);
    }

    private static int find(List<Character> board, Map<Character, Integer> hand) {
        cleanupBoard(board);
        if (board.size() == 0) return 0;
        if (empty(hand)) return -1;
        int count = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i<board.size(); i++) {
            char c = board.get(i);
            count++;
            if (i == board.size() - 1 || board.get(i+1) != c) {
                int missing = 3 - count;
                if (hand.get(c) >= missing) {
                    hand.put(c, hand.get(c) - missing);
                    List<Character> smallerBoard = new ArrayList<>(board);
                    for (int j = 0; j<count; j++) {
                        smallerBoard.remove(i-j);
                    }
                    int smallerFind = find(smallerBoard, hand);
                    if ( smallerFind != -1 ) {
                        min = Math.min(smallerFind + missing, min);
                    }
                    hand.put(c, hand.get(c) + missing);
                }
                count = 0;
            }
        }
        return (min == Integer.MAX_VALUE) ? -1 : min;
    }

    private static void cleanupBoard(List<Character> board) {
        int count = 0;
        boolean cleaned = false;
        for (int i = 0; i<board.size(); i++) {
            char c = board.get(i);
            count++;
            if (i == board.size() - 1 || board.get(i+1) != c) {
                if (count >= 3) {
                    for (int j = 0; j<count; j++) {
                        board.remove(i-j);
                    }
                    cleaned = true;
                    break;
                }
                count = 0;
            }
        }
        if (cleaned) {
            cleanupBoard(board);
        }
    }

    private static boolean empty(Map<Character,Integer> hand) {
        for (int val : hand.values()) {
            if (val > 0) return false;
        }
        return true;
    }

    /**
     *
     * Write a method that converts a integer to a String. Do not use something in the language
     * that does with a single command, it should be done manually, i.e. with a loop or other
     * method.
     * @param n Input integer
     * @return String that is the equivalent of input integer
     */
    static String intToStr(int n) {
        return null;
    }

    /**
     *
     * Write a method that converts a integer to a String. Do not use something in the language
     * that does with a single command, it should be done manually, i.e. with a loop or other
     * method.
     * @param n Input String
     * @return Integer that is the equivalent of input String
     */
    static int intToStr(String n) {
        return 0;
    }

    /**
     * Write a method that treats a string as a set of letters and exhaustively prints out all
     * possible subsets. This can be done by treating presence of absence of any item through
     * enumerating a binary number up to length of the string:
     *
     * 000
     * 001
     * 010
     * 011
     * 100
     * 101
     110
     111
     ->
     ->
     ->
     ->
     ->
     ->
     ->
     ->
     “”
     “c”
     “b”
     “bc”
     “a”
     “ac”
     “ab”
     “abc”
     static allSubsets(“abc”) -> [ “”, “c”, “b”, “bc”, “a”, “ac”, “ab”, “abc” ]
     *
     * @param s Input String
     * @return Subsets of input String
     */
    static String[] allSubsets(String s) {
        return null;
    }

    /**
     *
     * Write a method that rotates a string in place.
     String str = “abc123”; StrUtils.rotate(str, 2); print(str);
     > “23abc1”
     It shouldn't take more than O(n) time. There's a very clever algorithm for this.So for example if you have a String that is 800 letters long, and you want to rotate it 500
     positions, it shouldn't take 400,000 steps, it should only take like 5,000 steps or so.
     The clever algorithm is:
     1. Reverse the string: “abc123” -> “321cba”
     2. Reverse the subsection up to the position you need to shift:
     reverse(“321cba”,0,1) -> “231cba”
     3. Reverse the remaining subsection after that:
     reverse(“231cba”,2,5) -> “23abc1”
     Experiment by making a very long 10,000 letter long sample string, and then rotate it by 900
     positions to make sure it works efficiently.
     * It shouldn't take more than O(n) time. There's a very clever algorithm for this.So for example if you have a
     * String that is 800 letters long, and you want to rotate it 500
     *
     * @param s Input String
     * @return Rotated String
     */

    static String rotate(String s) {
        return null;
    }

    /**
     * Write a method that prints all permutations of a string.
     static allPermutations(“abc”) -> [ “abc”, “acb”, “bac”, “bca”, “cab”, “cba” ]
     static String[] allPermutations(String s) { ... }
     Algorithm:
     This uses recursion, so it's a challenging problem. The idea is to reduce the problem down
     into something manageable. The idea with recursion is to have a base case then handle every
     subcase properly. So the signature is going to be:

     Trace this out with “abc”. Does it actually work? Now code it.
     */
//
//    void recurse(String prefix) {
//        if (prefix.isEmpty()) {
//            System.out.println("\n");
//        }
//        else {
//            for (String letter: prefix) {
//                System.out.println(prefix.firstLetter());
//                prefix = prefix.removeFirstLetter()
//                perms(prefix)
//            }
//        }
//    }

    /**
     * Write a method that returns a string for a floating point number
     * float(100.1234, 3) -> “100.123”
     * float(100.1234, 0) -> “100”
     * @param r
     * @param precision
     * @return
     */
    String floatToString(float r, int precision) {
        return null;
    }

    /**
     * Write a method that replaces all instance of a substring with a new substring, and returns
     * new string with the result.
     *
     * Note: It should work the same your language's equivalent but do the work manually, so for
     * edge cases like:replace(“cccccccc”, “ccc”, “aaa”) -> it returns what your language does with this.
     * So you have to “guess” what algorithm your language actually uses to solve the problem.
     * Design a comprehensive set of edge “unit” tests to quickly help with this.
     * @param body
     * @param substr
     * @param replacement
     * @return
     */
    String replace(String body, String substr, String replacement) {
        return null;
    }

}
