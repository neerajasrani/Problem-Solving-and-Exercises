/**
 * Problem Statement

 You have a collection of sticks. The length of each stick is a positive integer.

 You want to have a collection of sticks in which all the sticks have the same length. You may alter your current collection by performing zero or more steps. Each step must look as follows:

 You choose one of your sticks. The chosen stick must have length at least 2.
 Let L be the length of the chosen stick.
 If L is even, cut the stick into two sticks of length L/2 each. Otherwise, cut it into sticks of lengths (L-1)/2 and (L+1)/2.
 Keep one of the two new sticks and throw away the other one.
 It can be proved that any collection of sticks can be turned into a collection of sticks that all have the same length. You are given the current lengths of your sticks in the int[] a. Compute and return the smallest number of steps needed to reach your goal.


 Definition

 Class:	Halving
 Method:	minSteps
 Parameters:	int[]
 Returns:	int
 Method signature:	int minSteps(int[] a)
 (be sure your method is public)


 Constraints
 -	a will contain between 2 and 50 elements, inclusive.
 -	Each element of a will be between 1 and 109, inclusive.

 Examples
 0)

 {11, 4}
 Returns: 3
 One optimal solution is:
 Pick the stick of length 11, cut it into sticks of lengths 5 and 6 and keep the part of length 5.
 Pick the stick of length 4, cut it into two sticks of length 2 and keep the part of length 2.
 Pick the stick of length 5, cut it into sticks of lengths 2 and 3 and keep the part of length 2.
 In the end, you'll have two sticks of length 2.
 1)

 {1000000000, 1000000000, 1000000000, 1000000000}
 Returns: 0
 All your sticks have the same length, no steps are needed.
 2)

 {1, 2, 3, 4, 5, 6, 7}
 Returns: 10
 3)

 {13, 13, 7, 11, 13, 11}
 Returns: 11
 4)

 {1, 1}
 Returns: 0
 */

public class StickProblem {

    public static void main(String[] args) {
        System.out.println(minSteps(new int[]{11, 4}));
        System.out.println(minSteps(new int[]{1000000000, 1000000000, 1000000000, 1000000000}));
        System.out.println(minSteps(new int[]{1, 2, 3, 4, 5, 6, 7}));
        System.out.println(minSteps(new int[]{13, 13, 7, 11, 13, 11}));
        System.out.println(minSteps(new int[]{1, 1}));
    }

    public static int minSteps(int[] a) {
        StickProblem.Stick[] sticks = new StickProblem.Stick[a.length];
        for (int i = 0; i < a.length; ++i) {
            sticks[i] = new StickProblem.Stick(a[i]);
        }
        int res = 0;
        while (true) {
            boolean updated = false;
            for (StickProblem.Stick s : sticks) {
                for (StickProblem.Stick t : sticks) {
                    if (t.max < s.min) {
                        updated = true;
                        s.min = Math.max(1, s.min / 2);
                        s.max = (s.max + 1) / 2;
                        ++res;
                    }
                }
            }
            if (!updated) break;
        }
        return res;
    }

    static class Stick {
        int min;
        int max;

        public Stick(int a) {
            min = a;
            max = a;
        }

    }

}


//public class StickProblem {
//
//    public static void main(String[] args) {
//        System.out.println(minSteps(new int[]{11, 4}));
//        System.out.println(minSteps(new int[]{1000000000, 1000000000, 1000000000, 1000000000}));
//        System.out.println(minSteps(new int[]{1, 2, 3, 4, 5, 6, 7}));
//        System.out.println(minSteps(new int[]{13, 13, 7, 11, 13, 11}));
//        System.out.println(minSteps(new int[]{1, 1}));
//    }
//
//    private static int minSteps(int[] a) {
//        /**
//         11, 4 - split into 6 and 5.
//         6,4
//         2
//
//         all sticks same length and min steps
//         gcd - 1...
//         5,6, 4
//         5, 4
//         4, 6 - 2
//
//         find smallest number
//         find GCD...
//         if no GCD
//
//
//         while true
//         for i in queue
//         keep splitting till
//
//         **/
//
//        StickProblem.Stick[] sticks = new StickProblem.Stick[a.length];
//
//        // populate sticks
//        for (int i=0; i<a.length; i++) {
//            sticks[i] = new StickProblem.Stick(a[i]);
//        }
//
//        int lowest = Integer.MAX_VALUE;
//        int steps = 0;
//
//        while (true) {
//            boolean sticksLengthAreEqual = true;
//            for (int i = 0; i < sticks.length; i++) {
//                if (sticks[i].min < lowest) {
//                    lowest = sticks[i].min;
//                    sticksLengthAreEqual = false;
//                } else if (sticks[i].min > lowest) {
//                    steps++;
//                    sticksLengthAreEqual = false;
//                    // a[i] is odd
//                    if ((sticks[i].min & 1) == 1) {
//                        int first = Math.max(1, sticks[i].min / 2);
//                        int second = (sticks[i].max + 1) / 2;
//                        sticks[i].min = first;
//                        sticks[i].max = second;
//                    }
//                    // a[i] is even
//                    else {
//                        sticks[i].min = Math.max(1, sticks[i].min / 2);
//                        sticks[i].max = (sticks[i].max + 1) / 2;
//                    }
////                    if (sticks[i].max < lowest) {
////                        lowest = sticks[i].max;
////                    } else if (sticks[i].min < lowest) {
////                        lowest = sticks[i].min;
////                    }
//
//                }
//            }
//            if (sticksLengthAreEqual) return steps;
//        }
//    }
//
//    static class Stick {
//        int min;
//        int max;
//
//        public Stick(int a) {
//            min = a;
//            max = a;
//        }
//
//    }
//}