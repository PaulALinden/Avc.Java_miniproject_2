package controller;

import model.Board;

public class MinMax {
    public static int minMax(Board board, int depth, boolean isMaximizingPlayer) {

        String marker;
        if (isMaximizingPlayer) {
            marker = "X";
        } else {
            marker = "O";
        }

        if (board.hasEmptyCells()) {
            board.createChildBoards(board, marker);
        }

        if (depth == 0 || board.getChildBoards().isEmpty()) {
            board.printBoard();
            return board.getValue();
        }

        if (isMaximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Board child : board.getChildBoards()) {
                child.checkWinner();
                int eval = minMax(child, depth - 1, false);
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Board child : board.getChildBoards()) {
                child.checkWinner();

                int eval = minMax(child, depth - 1, true);
                minEval = Math.min(minEval, eval);
            }
            return minEval;
        }
    }
}
