/**
 * Created by neeraj on 2/23/15.
 */
public class ParseArray {

    private int lastIndex;
    private Integer[] input;

    public static void main(String [] args) {
        ParseArray parseArray = new ParseArray();
        System.out.print(parseArray.returnLastElement(5) + "\n");
    }

    /**
     *
     * @return Object - Last remaining element of array
     */
    public int returnLastElement (int k) {

        this.input = new Integer[]  {
                1, 2, 3, 4, 5
        };
        this.lastIndex = input.length;

        int length = input.length;
        int tempK = k;

        while (lastIndex != 1) {

            if (k > lastIndex) {
                while (tempK > lastIndex) {
                    tempK = lastIndex - k;
                }
            } else {
                tempK = k;
            }
            rearrange(tempK);
            print();

        }

        return input[0];
    }

    private void rearrange(int k) {

        // Shift array elements to the left until "lastIndex" is reached.

        // Hold Kth value
        Integer holdK = input[k - 1];

        for (int i = k - 1; i < lastIndex - 2; i++) {
            input[i] = input[i+1];
        }

        input[lastIndex-1] = holdK;

        lastIndex--;
    }

    private void print() {
        for (int i : input) {
            System.out.print(i);
        }
        System.out.print("\n");

    }

}
