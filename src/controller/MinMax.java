package controller;

import model.Board;

public class MinMax {
    public static int minMax(Board firstChild, int depth, boolean isMaximizingPlayer) {
        int boardEval = firstChild.evalMove(depth);

        if (depth == 10 || !firstChild.hasEmptyCells(firstChild)) {
            return boardEval;
        }

        if (isMaximizingPlayer) {

            if (firstChild.getChildBoards().isEmpty()) {
                firstChild.createChildBoards(firstChild.getBoard(), "O");
            }

            int maxEval = Integer.MIN_VALUE;

            for (Board grandChild : firstChild.getChildBoards()) {
                int eval = minMax(grandChild, depth + 1, false);
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;

        } else {

            if (firstChild.getChildBoards().isEmpty()) {
                firstChild.createChildBoards(firstChild.getBoard(), "X");
            }

            int minEval = Integer.MAX_VALUE;
            for (Board grandChild : firstChild.getChildBoards()) {
                int eval = minMax(grandChild, depth + 1, true);
                minEval = Math.min(minEval, eval);
            }
            return minEval;
        }
    }
}