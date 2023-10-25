package controller;

import model.TwoPlayerBoard;

public class MinMax {
    public static int minMax(TwoPlayerBoard currentBoard, int depth, boolean isMaximizingPlayer) {

        int evaluateBoard = currentBoard.evaluateBoard();

        if (evaluateBoard == 10){return evaluateBoard-depth;}
        if (evaluateBoard == -10){return evaluateBoard+depth;}
        if (currentBoard.hasNoEmptyCells()) {return 0;}

        if (isMaximizingPlayer) {
            currentBoard.createPossibleBoards(currentBoard.getOpponent());
            int maxEval = Integer.MAX_VALUE;

            for (TwoPlayerBoard newBoard : currentBoard.getPossibleBoards()) {
                int eval = minMax(newBoard, depth + 1, false);
                maxEval = Math.min(maxEval, eval);
            }
            return maxEval;
        } else {
            currentBoard.createPossibleBoards(currentBoard.getPlayer());
            int minEval = Integer.MIN_VALUE;

            for (TwoPlayerBoard newBoard : currentBoard.getPossibleBoards()) {
                int eval = minMax(newBoard, depth + 1, true);
                minEval = Math.max(minEval, eval);
            }
            return minEval;
        }
    }
}