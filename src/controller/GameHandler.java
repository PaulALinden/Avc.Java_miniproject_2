package controller;

import model.Board;

import java.util.Objects;

import static controller.MinMax.minMax;

public class GameHandler {

    Board rootBoard;

    public GameHandler(Board rootBoard) {
        this.rootBoard = rootBoard;
    }

    public void playerMakeMove(int row, int col) {
        rootBoard.placeMarker("X", row, col);
    }

    public Board bestMoveForPlayer() {
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        createPossibleMoves("X");

        Board bestChild;
        int bestResult = Integer.MIN_VALUE;
        bestChild = getBestChild(bestResult, alpha, beta, true);

        return bestChild;
    }

    public void computerMakeMove() {
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        createPossibleMoves("O");
        // Check for best move
        int bestResult = Integer.MAX_VALUE;
        Board bestChild = getBestChild(bestResult, alpha, beta, false);

        // Apply the computer's move to the current board
        rootBoard.setBoard(bestChild.getBoard());
    }

    public Board getBestChild(int bestResult, int alpha, int beta, boolean isMaximizingPlayer) {
        Board bestChild = null;

        for (Board child : rootBoard.getChildBoards()) {
            child.evalMove();
            int result = minMax(child, 9, alpha, beta, isMaximizingPlayer);

            if (isMaximizingPlayer) {
                System.out.println("Spelares Poäng: " + result);
                child.printBoard();

                if (result > bestResult) {
                    bestResult = result;
                    bestChild = child;
                }

                alpha = Math.max(alpha, result);

            }else {
                System.out.println("Datorns Poäng: " + result);
                child.printBoard();

                if (result < bestResult) {
                    bestResult = result;
                    bestChild = child;
                }

                beta = Math.min(beta, result);
            }

            if (beta <= alpha) {
                break;
            }
        }
        return bestChild;
    }

    public void createPossibleMoves(String marker) {
        rootBoard.emptyChildBoards();
        rootBoard.createChildBoards(rootBoard, marker);
    }


}
