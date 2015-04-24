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
                h.clear();
            }
        }
        return input.substring(index, index + pre);
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
    }
}
