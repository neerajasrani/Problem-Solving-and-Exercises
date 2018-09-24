public class FindPathInMatrix {
    /**
     *
     *   Given a matrix of 0,1 of size n(rows)/m(columns)
         starting with top left corner, traverse the paths to bottom right corner.
         condition being if a cell has 0 you cannot go anywhere, if a cell has 1 you can right or down
         you can never go left or upward.

         Print all the possible paths for the traversal

         e.g.
         1,0,0,1
         1,1,0,0
         1,1,1,1

         Answer is 2
     *
     */

    private static int result = 0;

    public static void main(String[] args) {
        int[][] m = new int[][] {{1,0,0,1},{1,1,0,0},{1,1,1,1}};
        System.out.println(findPaths(m));
    }




    static int findPaths(int[][] m) {
        findPaths(m, 0, 0);
        return result;
    }

    static void findPaths (int[][] m, int i, int j) {
        // Closing condition
        if (i == m.length-1 && j == m[0].length -1) {
            result++;
        } else if (i == m.length-1 && m[i][j] == 1) {
            findPaths(m, i, j+1);
        } else if (j == m[0].length-1 && m[i][j] == 1) {
            findPaths(m, i+1, j);
        }
        else if(m[i][j] == 1) {
            findPaths(m, i+1, j);
            findPaths(m, i, j+1);
        }
    }
}
