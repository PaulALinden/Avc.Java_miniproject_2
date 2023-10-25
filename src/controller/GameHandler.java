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

    public boolean playerMakeMove(int row, int col) {
        if (Objects.equals(rootBoard.getBoard()[row][col], rootBoard.getEmpty())) {
            this.rootBoard.addMarkerToBoard(rootBoard.getPlayer(), row, col);
            return true;
        }
        return false;
    }

    public TwoPlayerBoard optimalMovePlayer() {
        createPossibleMoves(rootBoard.getPlayer());
        TwoPlayerBoard bestMove = null;

        int bestResult = Integer.MIN_VALUE;

        for (TwoPlayerBoard possibleBoards : this.rootBoard.getPossibleBoards()) {

            int boardEval = minMax(possibleBoards, 0, true);

            if (boardEval > bestResult) {
                bestResult = boardEval;
                bestMove = possibleBoards;
            }
        }
        return bestMove;
    }

    public void optimalMoveComputer() {
        createPossibleMoves(rootBoard.getOpponent());
        TwoPlayerBoard bestComputerMove = null;

        int bestResult = Integer.MAX_VALUE;

        for (TwoPlayerBoard possibleBoards : this.rootBoard.getPossibleBoards()) {

            int boardEval = minMax(possibleBoards, 0, false);

            if (boardEval < bestResult) {
                bestResult = boardEval;
                bestComputerMove = possibleBoards;
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