package controller;

import model.Board;

import static controller.MinMax.minMax;

public class GameHandler {

    Board rootBoard;

    public GameHandler(Board rootBoard) {
        this.rootBoard = rootBoard;
    }

    public void playerMakeMove(int row, int col) {
        rootBoard.placeMarker("X", row, col);
    }

    public Board playerMakeMove() {
        initPossibleMoves("X");
        Board bestChild = null;

        int currentBestResult = Integer.MIN_VALUE;
        int count = 0;

        for (Board child : rootBoard.getChildBoards()) {
            int result = minMax(child, 7, false);
            count++;
            System.out.println("Result of player board " + count + ": " + result);
            child.printBoard();

            if (result > currentBestResult) {
                currentBestResult = result;
                bestChild = child;
            }
        }
        return bestChild;
    }

    public void computerMakeMove() {
        initPossibleMoves("O");
        Board bestCompChild = null;

        int currentBestResult = Integer.MAX_VALUE;
        int count = 0;

        for (Board child : rootBoard.getChildBoards()) {
            int result = minMax(child, 0, true);
            count++;
            System.out.println("Result of computer board " + count + ": " + result);
            child.printBoard();

            if (result < currentBestResult) {
                currentBestResult = result;
                bestCompChild = child;
            }
        }
        assert bestCompChild != null;
        rootBoard.setBoard(bestCompChild.getBoard());
    }

    public void initPossibleMoves(String marker) {
        rootBoard.getChildBoards().clear();
        rootBoard.createChildBoards(rootBoard.getBoard(), marker);
    }
}