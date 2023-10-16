package controller;

import model.Board;

public class MinMax {
    public static int minMax(Board board, int depth, int alpha, int beta, boolean isMaximizingPlayer) {
        board.evalMove();

        String marker;
        if (isMaximizingPlayer) {
            marker = "O";
        } else {
            marker = "X";
        }

        if (board.hasEmptyCells()) {
            board.createChildBoards(board, marker);
        }

        if (depth == 0 || board.getChildBoards().isEmpty()) {
            return board.getValue()-depth;
        }

        if (isMaximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Board child : board.getChildBoards()) {
                int eval = minMax(child, depth - 1, alpha, beta, false);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Board child : board.getChildBoards()) {
                int eval = minMax(child, depth - 1, alpha, beta, true);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return minEval;
        }
    }
}