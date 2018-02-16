/**
 * Problem Statement
 * A chessboard consists of alternating black and white squares: every square differs in color from the squares that are adjacent to it vertically and horizontally.
 * <p>
 * You are given a rectangular board of black and white squares in a board. However, the coloring of the squares might not match the chessboard coloring scheme. Each string in board represents a row of squares of the board, and each character (all either 'B' or 'W') represents a square in that row. Return the minimum number of squares that would need their color swapped for the given board to become a valid chessboard.
 * <p>
 * Definition
 * Class: BrokenChessboard
 * Method: minimumFixes
 * Parameters: String[]
 * Returns: int
 * Method signature: int minimumFixes(String[] board)
 * (be sure your method is public)
 * Limits
 * Time limit (s): 2.000
 * Memory limit (MB): 256
 * Constraints
 * - board will contain between 1 and 50 elements, inclusive.
 * - Each element of board will contain between 1 and 50 characters, inclusive.
 * - Each element of board will be the same length.
 * - Each character of each element of board will be either 'B' or 'W'.
 * Examples
 * 0)
 * {"BWB", "BBW", "BWW"}
 * Returns: 2
 * There are two ways to fix this board:
 * BWB  WBW
 * WBW  BWB
 * BWB  WBW
 * The result on the left would require changing 2 squares (center-left and bottom-right). The result on the right would mean changing 7 squares, so the former is a better choice.
 * 1)
 * {"WW", "WW"}
 * Returns: 2
 * Either of the two valid boards we could make would take exactly 2 changes.
 * 2)
 * {"BWB", "WBW", "BWB"}
 * Returns: 0
 * This board is already valid, so no fixes are needed.
 * 3)
 * {"B", "W", "B", "B"}
 * Returns: 1
 * The board is not square, but anyways it only needs 1 square changed.
 * 4)
 * {"BWBBB", "WWBBW", "BBBBW", "WBWBB"}
 * Returns: 7
 * This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
 */


public class BrokenChessboard {

    public static void main(String[] args) {
        String[] cb = new String[] {"BWBBB", "WWBBW", "BBBBW", "WBWBB"};
//        String[] cb = new String[] {"WW", "WW"};
//        String[] cb = new String[] {"BWB", "WBW", "BWB"};
//        String[] cb = new String[] {"B", "W", "B", "B"};
//        String[] cb = new String[]{"BW", "WB"};


        int result = minimumFixes(cb, "betterSolution");

        System.out.println(result);

    }


    public static int minimumFixes(String[] board, String betterSolution) {

        char blackColor = 'B';
        char whiteColor = 'W';
        int chessRowLength = board[0].length();
        int result = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < chessRowLength; j++) {
                char c;
//                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) c = 'W';
                if (((i&1)^(j&1))==0) c = 'W';
                else c = 'B';
                if (board[i].charAt(j) == c) ++result;
            }
        }
        return Math.min(result, chessRowLength * board.length - result);
    }


    public static int minimumFixes(String[] board) {

        char blackColor = 'B';
        char whiteColor = 'W';

        return Math.min(doFixBoard(board, whiteColor), doFixBoard(board, blackColor));
    }

    public static int doFixBoard(String[] cb, char startColor) {
        int chessRowLength = cb[0].length();
        int chessBoardLength = cb.length;
        String chessRow = null;
        int result = 0;
        boolean chessRowLenEven = chessRowLength % 2 == 0;

        for (String aCb : cb) {
            chessRow = aCb;
            if (chessRowLenEven) startColor = toggleColor(startColor);
            for (int j = 0; j < chessRowLength; j++) {
                if (chessRow.charAt(j) != startColor) {
                    result++;
                }
                startColor = toggleColor(startColor);

            }
        }
        return result;
    }

    public static char toggleColor(char inputColor) {
        return inputColor == 'W' ? 'B' : 'W';
    }
}