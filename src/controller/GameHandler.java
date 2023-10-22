package controller;

import model.GameStates;
import model.TwoPlayerBoard;

import java.util.Objects;

import static controller.MinMax.minMax;

public class GameHandler {
    TwoPlayerBoard rootBoard;
    public GameHandler(TwoPlayerBoard rootBoard) {
        this.rootBoard = rootBoard;
    }
    public boolean setMarker(int row, int col) {
        if (Objects.equals(rootBoard.getBoard()[row][col], " ")) {
            this.rootBoard.addMarkerToBoard("X", row, col);
            return true;
        }
        return false;
    }
    public TwoPlayerBoard optimalMovePlayer() {
        createPossibleMoves("X");
        TwoPlayerBoard bestMove = null;

        int bestResult = Integer.MIN_VALUE;
        int count = 0;

        for (TwoPlayerBoard possibleBoards : this.rootBoard.getPossibleBoards()) {

            int boardEval = minMax(possibleBoards, 0, true);
            //For Debugging
            count++;
            System.out.println("Result of player board " + count + ": " + boardEval);
            possibleBoards.displayBoard();

            if (boardEval > bestResult) {
                bestResult = boardEval;
                bestMove = possibleBoards;
            }
        }
        return bestMove;
    }
    public void optimalMoveComputer() {
        createPossibleMoves("O");
        TwoPlayerBoard bestComputerMove = null;

        int bestResult = Integer.MAX_VALUE;
        int count = 0;

        for (TwoPlayerBoard child : this.rootBoard.getPossibleBoards()) {
            int boardEval = minMax(child, 0, true);

            //For Debugging
            count++;
            System.out.println("Result of computer board " + count + ": " + boardEval);
            child.displayBoard();

            if (boardEval < bestResult) {
                bestResult = boardEval;
                bestComputerMove = child;
            }
        }
        if (bestComputerMove != null) {
            rootBoard.setBoard(bestComputerMove.getBoard());
        }
    }
    private void createPossibleMoves(String marker) {
        this.rootBoard.getPossibleBoards().clear();
        this.rootBoard.createPossibleBoards(marker);
    }
    public GameStates hasResult() {
        this.rootBoard.checkBoardState();
       return this.rootBoard.getGameStates();
    }
}