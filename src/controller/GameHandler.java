package controller;

import model.Board;

import java.util.ArrayList;
import java.util.List;

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
        bestChild = getBestChild(alpha, beta, true);

        return bestChild;
    }

    public void computerMakeMove() {
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        createPossibleMoves("O");
        // Check for best move
        Board bestChild = getBestChild(alpha, beta, false);

        // Apply the computer's move to the current board
        rootBoard.setBoard(bestChild.getBoard());
    }

    public Board getBestChild(int alpha, int beta, boolean isMaximizingPlayer) {
        List<Board> bestChildren = new ArrayList<>();
        int currentBestResult = isMaximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (Board child : rootBoard.getChildBoards()) {
            int result = minMax(child, 9, alpha, beta, isMaximizingPlayer);

            if (isMaximizingPlayer) {
                if (result > currentBestResult) {
                    currentBestResult = result;
                    bestChildren.clear(); // Clear previous best children
                    bestChildren.add(child); // Add the new best child
                } else if (result == currentBestResult) {
                    bestChildren.add(child); // Add child with the same value
                }
            } else {
                if (result < currentBestResult) {
                    currentBestResult = result;
                    bestChildren.clear(); // Clear previous best children
                    bestChildren.add(child); // Add the new best child
                } else if (result == currentBestResult) {
                    bestChildren.add(child); // Add child with the same value
                }
            }
        }

        if (!bestChildren.isEmpty()) {

            for (Board evalBest : bestChildren) {
               int intialRow = evalBest.getRowCord();
               int initialColumn = evalBest.getColumnCord();

                for (Board opponentChild : evalBest.getChildBoards()) {

                    if (isMaximizingPlayer) {
                        if (opponentChild.isWinner()) {
                            System.out.println("PLa");
                            opponentChild.printBoard();

                            int row = opponentChild.getRowCord();
                            int col = opponentChild.getColumnCord();

                            System.out.println(row);
                            System.out.println(col);

                            String[][] blockMove = opponentChild.getBoard();
                            blockMove[intialRow][initialColumn] = " ";
                            blockMove[row][col] = "X";

                            opponentChild.setBoard(blockMove);

                            return opponentChild;
                        }
                    }
                }
            }
            return bestChildren.get(0);
        }
        return null;
    }

    public void createPossibleMoves(String marker) {
        rootBoard.emptyChildBoards();
        rootBoard.createChildBoards(rootBoard, marker);
    }
}