/**
 * Created by nickamac on 8/7/17.
 */
public class RecursionProblem1 {

    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        int d = Integer.parseInt(args[3]);

        String result = isPossible(a,b,c,d);

        System.out.println(result);
    }

    static String isPossible(int a, int b, int c, int d) {
        if (a < 1 || b < 1 || c < 1 || d < 1 || a > 1000 || b > 1000 || c > 1000 || d > 1000) {
            return "No";
        }

        String result = recursiveCompute(a, b, c, d);

        if (result.equals("Yes")) {
            return result;
        }
        else {
            return "No";
        }
    }

    static String recursiveCompute(int a, int b, int c, int d) {

        // end condition
        if (a == c && b == d) {
            return "Yes";
        }
        // sub problem
        if (a < c) { // 1, 5
            return isPossible(a+b, b, c, d);
        }
        if (b < d) { // 4, 9
            return isPossible(a, a+b, c, d);
        }
        return "No";
    }
}
