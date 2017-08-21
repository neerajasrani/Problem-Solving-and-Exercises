import java.util.ArrayList;
import java.util.List;

/**
 * Created by nickamac on 8/11/17.
 */
public class TopTal {
    public static void main(String[] args) {
        int[] A = new int[] {1, 5, 3, 3, 7};
        int[] B = new int[] {1, -1, 0, 2, 3, 5};
        int result = solution(B, 3);
        System.out.println(result);
    }

    public static int solution(int[] A, int D) {
        // write your code in Java SE 8
        int minTime = 0;
        int pos = 0;

        if (A.length - 1 - D == 0) {
            return -1;
        }
        else {
            int l = A.length;
            List<Integer> list = new ArrayList<>();
            while (l - D >= 0) {
                list.add(findMinTime(A, l-D, l-1));
                l = l-D;
            }
            minTime = findMaxTime(list);
            return minTime;
        }
    }

    public static int findMinTime (int[] A, int minIndex, int maxIndex) {
        Integer minTime = 0;
        for (int i = minIndex; i <= maxIndex; i++) {
            if (A[i] == -1) continue;
            if(A[i] < minTime) {
                minTime = A[i];
            }
        }
        return minTime;
    }

    public static int findMaxTime (List<Integer> list) {
        Integer max = -1;
        for (Integer i: list) {
            if(i > max) {
                max = i;
            }
        }
        return max;
    }

    public static boolean solution(int[] A) {
        // write your code in Java SE 8

        int countOfNonDecreasingNumbers = 0;
        int index1 = 0;
        int index2 = 0;

        if (A.length == 1 || A.length == 2) {
            return true;
        }

        for (int i =0; i < A.length; i ++) {
            if (i < A.length - 1 && A[i] > A[i+1]) {
                countOfNonDecreasingNumbers++;
                index1 = i;
            }
            if(countOfNonDecreasingNumbers > 1) {
                return false;
            }
            else if(A[i] >= A[index1]){
                index2 = i;
            }
            System.out.format("Index 1 : %d and Index 2 : %d\n", index1, index2);
        }

        if (index2 > index1) {
            return true;
        }
        return false;
    }

    class Tree
    {
        public int x;
        public Tree l;
        public Tree r;
    };

    class Task3
    {
        public int solution(Tree T)
        {
            return countZigZag(T, 0) - 1;
        }

        int countZigZag(Tree t, int max)
        {
            if (t == null) return 0;

            int leftTree = countTurns(t, true, 0);
            int rightTree = countTurns(t, false, 0);

            max = Math.max(leftTree, rightTree);
            max = Math.max(max, countZigZag(t.l, max));
            max = Math.max(max, countZigZag(t.r, max));

            return max;
        }

        int countTurns(Tree node, boolean canTurn, int c)
        {
            if (node == null) return c;

            if (canTurn) {
                c = countTurns(node.l, false, node.l == null ? c : c + 1);
            } else {
                c = countTurns(node.r, true, node.r == null ? c : c + 1);
            }

            return c;
        }
    }



}
