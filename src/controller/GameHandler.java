package controller;

import model.Board;

import static controller.MinMax.minMax;

public class GameHandler {

    Board rootBoard;
    public GameHandler(Board rootBoard) {
        this.rootBoard = rootBoard;
    }
    public void setMarker(int row, int col) {
        this.rootBoard.addMarkerToBoard("X", row, col);
    }
    public Board optimalMovePlayer() {
        createPossibleMoves("X");
        Board bestMove = null;

        int bestResult = Integer.MIN_VALUE;
        int count = 0;

        for (Board possibleBoards : this.rootBoard.getPossibleBoards()) {

            int boardEval = minMax(possibleBoards, 0, true);
            //For Debugging
            count++;
            System.out.println("Result of player board " +count+ ": " + boardEval);
            possibleBoards.printBoard();

                if (boardEval > bestResult) {
                    bestResult = boardEval;
                    bestMove = possibleBoards;
                }
        }
        return bestMove;
    }
    public void optimalMoveComputer() {
        createPossibleMoves("O");
        Board bestComputerMove = null;

        int bestResult = Integer.MAX_VALUE;
        int count = 0;

        for (Board child : this.rootBoard.getPossibleBoards()) {
            int boardEval = minMax(child, 0, true);

            //For Debugging
            count++;
           System.out.println("Result of computer board " +count+ ": " + boardEval);
           child.printBoard();

            if (boardEval < bestResult) {
                bestResult = boardEval;
                bestComputerMove = child;
            }
        }
        if (bestComputerMove != null) {
            rootBoard.setBoard(bestComputerMove.getBoard());
        }
    }

    public void createPossibleMoves(String marker) {
        this.rootBoard.getPossibleBoards().clear();
        this.rootBoard.createPossibleBoards(marker);
    }
}