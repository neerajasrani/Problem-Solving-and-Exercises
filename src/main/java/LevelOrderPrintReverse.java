import java.util.ArrayList;
import java.util.List;

public class LevelOrderPrintReverse {
    /**
         3
                 4     6
                 8     9
                 14   28


    Print the binary tree in spiral order.
 3,4,6,9,8,14,28


    Stack looks like -

     result, 3, 0
     result, 4, 1
     result, null, 2
     result, null, 2
     result, 6, 1
     result, 8, 2
     result, null, 3
     result, null, 3
     result, 9, 2
     result, 14, 3
     result, null, 4
     result, null, 4
     result, 28, 3
     result, null, 4
     result, null, 4

     [3]
     [4,6]
     [8, 9]
     [14, 28]

     **/

    private static void storeLevelOrder(List<List<Integer>> result , LevelOrderTraversal.TreeNode root, int height) {
        if (root == null) {
            return;
        }

        if(result.size() == height) {
            List<Integer> levelResult = new ArrayList<Integer>();
            levelResult.add(root.val);
            result.add(levelResult);
        } else {
            List<Integer> levelResult = result.get(height);
            levelResult.add(root.val);
        }

        storeLevelOrder(result, root.left, height+1);
        storeLevelOrder(result, root.right, height+1);

    }

    public static void main (String [] args) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();

        LevelOrderTraversal.TreeNode root = new LevelOrderTraversal.TreeNode(3);
        root.left = new LevelOrderTraversal.TreeNode(4);
        root.right = new LevelOrderTraversal.TreeNode(6);
        root.right.left = new LevelOrderTraversal.TreeNode(8);
        root.right.right = new LevelOrderTraversal.TreeNode(9);
        root.right.right.left = new LevelOrderTraversal.TreeNode(14);
        root.right.right.right = new LevelOrderTraversal.TreeNode(28);

        storeLevelOrder (result, root, 0);


        /*
         [3]
         [4,6]
         [8, 9]
         [14, 28]

         3 , 4 , 6, 9, 8, 14, 28 ,
         */
        for (int i = 0; i < result.size(); i++) {
            List<Integer> levelResult = result.get(i);

            if (i % 2 !=0) {
                // Ascending order
                for (Integer aLevelResult : levelResult) {
                    System.out.println(aLevelResult + " ,");
                }
            } else {
                // Descending order
                for(int j = levelResult.size() - 1; j >=0 ; j--) {
                    System.out.println(levelResult.get(j) + " ,");
                }
            }
        }
    }
}
