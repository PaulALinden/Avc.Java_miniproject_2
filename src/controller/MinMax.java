package controller;

import model.Board;

public class MinMax {
    public static int minMax(Board currentBoard, int depth, boolean isMaximizingPlayer) {

        int boardEval = currentBoard.evaluateBoard();

        if (boardEval == 10) {
            return boardEval - depth;
        }

        if (boardEval == -10) {
            return boardEval + depth;
        }

        if (depth == 10 || !currentBoard.hasEmptyCells(currentBoard)) {
            return boardEval;
        }

        if (isMaximizingPlayer) {

            currentBoard.createChildBoards(currentBoard.getBoard(), "O");
            int maxEval = Integer.MIN_VALUE;

            for (Board newBoard : currentBoard.getChildBoards()) {
                int eval = minMax(newBoard, depth + 1, false);
                maxEval = Math.max(maxEval, eval);
            }

            return maxEval;
        } else {

            currentBoard.createChildBoards(currentBoard.getBoard(), "X");
            int minEval = Integer.MAX_VALUE;

            for (Board grandChild : currentBoard.getChildBoards()) {
                int eval = minMax(grandChild, depth + 1, true);
                minEval = Math.min(minEval, eval);
            }

            return minEval;
        }
    }
}