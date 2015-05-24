import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by neeraj on 4/23/15.
 */
public class Facebook {

    /*    Given an input string and an alphabet, you need to find the minimum substring in the input string that contains each letter of the alphabet at least once

        input: aaccbb
        alphabet: abc
        output: accb

        aacdacb
        acacb
        aacdab

        O(n) * O(l)

        O(n)*/
    public static String minSubstring(String input, String al) {

        HashMap<Character, Integer> h = new HashMap<Character, Integer>();
        int pre =Integer.MAX_VALUE;
        int index = 0;
        int size = 0;

        HashSet<Character> alp = new HashSet<Character>();

        for (Character c : al.toCharArray()) {
            alp.add(c);
        }

        for (int i = 0; i < input.length(); i++) {
            if (alp.contains(input.charAt(i))) { // O(1)
                h.put(input.charAt(i), i); // a - 2, c - 3, b -4
            }

            if (checkIfHashmapHasAllAlphabets(h, alp)) {
                int[] r = computeSize(h);
                size = r[0];
                index = r[1];
                pre = Math.min(pre, size);
            }
        }
        return input.substring(index, index + pre);
    }


    /*    Given a map of numbers to letters, find out given a input string, what are the number of combinations that
    can be made with that input String. Note that "0" does not map to anything.

    0
    "1" - "A"
    "2" - "B"
    "3" - "C"
    "4" - "D" -
    "5" - "E"
    "6" - "F"
    "7" - "G"
    "8" - "H"
    "9" - "I"
    "10" - "J"
    "11" - "K"
    "12" - "L"
    "13" - "M"
    "14" - "N"
    "15" - "O"
    "16" - "P"
    "17" - "Q"
    "18" - "R"
    "19" - "S"
    "20" - "T"
    "21" - "U"
    "22" - "V"
    "23" - "W"
    "24" - "X"
    "25" - "Y"
    "26" - "Z"


    input: "101"
    JA
    Output: 1

    input: "111"
    AAA
    KA
    AK
    Output: 3

    */
    public static int findStringCombinations(String in) {

        HashMap<String, String> h = new HashMap<String, String>();
        // Values do not matter since we have to return count of combinations not the combinations themselves.
        h.put("1", "A");
        h.put("2", "A");
        h.put("3", "A");
        h.put("4", "A");
        h.put("5", "A");
        h.put("6", "A");
        h.put("7", "A");
        h.put("8", "A");
        h.put("9", "A");
        h.put("10", "A");
        h.put("11", "A");
        h.put("12", "A");
        h.put("13", "A");
        h.put("14", "A");
        h.put("15", "A");
        h.put("16", "A");
        h.put("17", "A");
        h.put("18", "A");
        h.put("19", "A");
        h.put("20", "A");
        h.put("21", "A");
        h.put("22", "A");
        h.put("23", "A");
        h.put("24", "A");
        h.put("25", "A");
        h.put("26", "A");

        if(in == null) {
            return 0;
        }
        if (in.length() == 1) {
            if(in.equals("0")) {
                return 0;
            } else {
                return 1;
            }
        }
        int[] dp = new int[in.length()];

        for (int i = 0; i <= in.length() - 1; i++) {
            int c=0;
            if(in.charAt(i) == '0')  {
                if (i-1 >=0) {
                    if(in.charAt(i-1) == '1' || in.charAt(i-1) == '2') {
                        if (i-2 < 0) {
                            dp[i] = 0;
                        } else {
                            dp[i] = 0;
                            dp[i-1] = 0;
                        }
                    }
                }
                else {
                    return 0;
                }
            }
            else {
                if(i-1 >= 0 && in.charAt(i-1) != '0') {
                    if (h.containsKey(in.substring(i-1, i+1))) {
                        c++;
                    }
                }
                if(i+1 <= in.length() - 1) {
                    if (h.containsKey(in.substring(i, i+2))) {
                        c++;
                    }
                }
                if(i+2 <= in.length()-1) {
                    if (in.charAt(i+2) == '0') {
                        c--;
                    }
                }
            }

            dp[i] = c;

        }


        int result = 0;

            for (int j = 0; j <= dp.length - 1; j++) {
                result += dp[j];
                System.out.print(dp[j]);
            }

        return result;
    }



    private static boolean checkIfHashmapHasAllAlphabets(HashMap<Character, Integer> h , HashSet<Character> al) {
        Iterator<Character> iter = al.iterator();
        boolean containsAll = true;

        while (iter.hasNext()) {
            if(!h.containsKey(iter.next()))
                containsAll = false;
        }

        return containsAll;
    }


    private static int[] computeSize(HashMap<Character, Integer> h) {

        int s = Integer.MAX_VALUE;
        int l = 0;

        for (Map.Entry<Character, Integer> e : h.entrySet()) {
            if (s > e.getValue() ) {
                s = e.getValue();
            }
            if (l < e.getValue()) {
                l = e.getValue();
            }
        }

        return new int[]{l - s + 1, s};

    }

    public static void main(String[] args) {
        System.out.print(minSubstring("aaccbb", "abc") + "\n");
        System.out.print(minSubstring("aacdacb", "abc")+ "\n");
        System.out.print(minSubstring("aacdab", "abc")+ "\n");
        System.out.print(minSubstring("aabbca", "abc")+ "\n");

        System.out.print("\n" + findStringCombinations("10")+ "\n");
        System.out.print("\n" + findStringCombinations("101")+ "\n");
        System.out.print("\n" + findStringCombinations("111")+ "\n");
        System.out.print("\n" + findStringCombinations("21121209")+ "\n");
        System.out.print("\n" + findStringCombinations("13134291")+ "\n");
        System.out.print("\n" + findStringCombinations("1111")+ "\n");
        System.out.print("\n" + findStringCombinations("13131231")+ "\n");
        System.out.print("\n" + findStringCombinations("0")+ "\n");
        System.out.print("\n" + findStringCombinations("01")+ "\n");
        System.out.print("\n" + findStringCombinations("11")+ "\n");

    }
}
