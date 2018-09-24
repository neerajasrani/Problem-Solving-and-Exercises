/**
 * You are given two int[]s L and R, each of length n.

 Find the number of strictly increasing sequences of integers A[0] < A[1] < ... < A[n-1] such that L[i] ≤ A[i] ≤ R[i] for every i. Return this number modulo 998244353.


 Definition

 Class:	IncreasingSequences
 Method:	count
 Parameters:	int[], int[]
 Returns:	int
 Method signature:	int count(int[] L, int[] R)
 (be sure your method is public)


 Constraints
 -	n will be between 1 and 300, inclusive.
 -	L will contain exactly n elements.
 -	R will contain exactly n elements.
 -	L[i] will be between 1 and 109, inclusive.
 -	R[i] will be between L[i] and 109, inclusive.

 Examples
 0)

 {1, 3, 1, 4}
 {6, 5, 4, 6}
 Returns: 4
 There are 4 strictly increasing sequences satisfying the conditions: {1, 3, 4, 5}, {1, 3, 4, 6}, {2, 3, 4, 5} and {2, 3, 4, 6}.
 1)

 {10, 30}
 {20, 40}
 Returns: 121
 2)

 {30, 10}
 {40, 20}
 Returns: 0
 3)

 {4, 46, 46, 35, 20, 77, 20}
 {41, 65, 84, 90, 49, 86, 88}
 Returns: 2470
 4)

 {1}
 {1000000000}
 Returns: 1755647
 Don't forget about the modulo.

 */

public class IncreasingSequences {

}
