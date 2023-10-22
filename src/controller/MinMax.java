package controller;

import model.TwoPlayerBoard;

public class MinMax {
    public static int minMax(TwoPlayerBoard currentBoard, int depth, boolean isMaximizingPlayer) {

        int boardEval = currentBoard.evaluateBoard();

        if (boardEval == 10) {return boardEval;}
        if (boardEval == -10) {return boardEval;}
        if (depth == 10 || currentBoard.hasNoEmptyCells()) {return boardEval;}

        if (isMaximizingPlayer) {

            currentBoard.createPossibleBoards(currentBoard.getOpponent());
            int maxEval = Integer.MIN_VALUE;

            for (TwoPlayerBoard newBoard : currentBoard.getPossibleBoards()) {
                int eval = minMax(newBoard, depth + 1, false);
                maxEval = Math.max(maxEval, eval)-depth;
            }
            return maxEval;
        } else {
            currentBoard.createPossibleBoards(currentBoard.getPlayer());
            int minEval = Integer.MAX_VALUE;

            for (TwoPlayerBoard newBoard : currentBoard.getPossibleBoards()) {
                int eval = minMax(newBoard, depth + 1, true);
                minEval = Math.min(minEval, eval)+depth;
            }
            return minEval;
        }
    }
}