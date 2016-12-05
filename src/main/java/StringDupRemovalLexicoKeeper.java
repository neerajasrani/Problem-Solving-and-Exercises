import java.util.ArrayList;

/**
 * Created by neeraj on 11/14/16.
 * Given a string which only contains lowercase. You need delete the repeated letters only leave one, and try to make the lexicographical order of new string is smallest.
 * i.e:
 * bcabc
 * You need delete 1 'b' and 1 'c', so you delete the first 'b' and first 'c', the new string will be abc which is smallest.
 * <p>
 * ps: If you try to use greedy algorithm to solve this problem, you must sure that you could pass this case:
 * cbacdcbc. answer is acdb not adcb
 * <p>
 * I can't solve this problem well and the interviewer said that you can scan the string twice. First scan is do some preprocess, and the second is to get the answer, but I really can't come up this idea.
 */
public class StringDupRemovalLexicoKeeper {

    private static CustomArrayList customArrayList;

    public static void main(String[] args) throws Exception {
        // input string
        String in = "lmznopnbdfikmonqsvnlmznop"; //      bdfikmnqsvlzop
//        String in = "omdzozd";
//        String in = "o";
//        String in = "oo";
//        String in = "mmm";
//        String in = "omdzozd";

        // ds used for this problem
        customArrayList = new CustomArrayList();

        // populate ds
        int charIndex = 0;
        for (char ch : in.toCharArray()) {
            customArrayList.insert(ch, charIndex);
            charIndex++;
        }

        // begin algo
        charIndex = 0;
        while (charIndex < customArrayList.size() - 1) {
            // forward swap
            boolean isSwapped = swap(charIndex);
            if (isSwapped) {
                // if forward swap, then backward swap
                // we start from a previous position from current index to enable backward swapping
                int charIndexForBckwdSwap = charIndex - 1;
                while (charIndexForBckwdSwap >= 0) {
                    boolean isBckwdSwapped = swap(charIndexForBckwdSwap);
                    if (isBckwdSwapped) {
                        charIndexForBckwdSwap--;
                    } else {
                        break;
                    }
                }
            }
            charIndex++;
        }
        // end algo

        for (StringPosition sp : customArrayList) {
            System.out.println(sp);
        }

    }

    private static boolean swap(int charIndex) {

        StringPosition sp = customArrayList.get(charIndex);
        StringPosition sp2 = customArrayList.get(charIndex + 1);

        boolean swap = true;
        int minSpPos = sp.getPos().get(0);

        for (int sp2Pos : sp2.getPos()) {
            if (minSpPos < sp2Pos) {
                swap = false;
            }
        }
        if (swap) {
            customArrayList.set(charIndex, sp2);
            customArrayList.set(charIndex + 1, sp);
        }
        return swap;
    }
}


class CustomArrayList extends ArrayList<StringPosition> {

    void insert(char c, Integer in) throws Exception {

        boolean result = false;

        // when array is initially empty we need to add
        if (size() == 0) {
            add(new StringPosition(c, in));
            return;
        }

        // when array has more than one element
        for (int i = 0; i < this.size(); i++) {

            // if same alphabet repeats
            if (get(i).getAlpha() == c) {
                get(i).getPos().add(in);
                result = true;
                break;
            }
            // if ascii value of incoming character is less than current arraylist element's alphabet ascii value
            // a (incoming) < b (current arraylist element)
            else if ((int) (c) < (int) (get(i).getAlpha())) {
                // special case when i=0 to prevent indexOutOfBoundException
                if (i == 0) {
                    StringPosition oldVal = set(0, new StringPosition(c, in));
                    add(1, oldVal);
                }
                // insert incoming character before current arraylist element
                else
                    add(i, new StringPosition(c, in));
                result = true;
                break;
            }
        }

        // add to end of list, incoming character is greater than all elements currently in arraylist
        if (!result && size() != 0) {
            add(new StringPosition(c, in));
        }
    }
}


class StringPosition {

    private char alpha;
    private ArrayList<Integer> pos;

    StringPosition(char alpha, Integer in) {
        this.alpha = alpha;
        this.pos = new ArrayList<>(5);
        this.pos.add(in);
    }

    ArrayList<Integer> getPos() {
        return pos;
    }

    void setPos(ArrayList<Integer> pos) {
        this.pos = pos;
    }

    void appendPos(Integer i) {
        pos.add(i);
    }

    char getAlpha() {
        return alpha;
    }

    void setAlpha(char alpha) {
        this.alpha = alpha;
    }

    @Override
    public String toString() {
        return Character.toString(this.getAlpha())  + " - " + this.getPos();
    }

}