import java.util.ArrayList;
import java.util.List;

public class LevelOrderTraversal {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x;}
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.left.right = new TreeNode(7);

        List<List<Integer>> result = findLevelOrder(root);

        for (List<Integer> levelResult: result) {
            for (Integer i : levelResult) {
                System.out.println(i);
            }
        }
    }

    public static List<List<Integer>> findLevelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        storeLevelOrder(result, root, 0);
        return result;
    }



    public static void storeLevelOrder (List<List<Integer>>result, TreeNode root, int height) {
        if (root == null) {
            return;
        }
        // For leftmost node(s) in subtree when level result is just created
        if (result.size() <= height) {
            List<Integer> levelResult = new ArrayList<Integer>();
            levelResult.add(root.val);
            result.add(levelResult);
        }
        // For left and right subtree with same height
        else {
            List<Integer> levelResult = result.get(height);
            levelResult.add(root.val);
        }
        storeLevelOrder(result, root.left, height+1);
        storeLevelOrder(result, root.right, height+1);
    }
}


