package model;

import java.util.ArrayList;
import java.util.Objects;

public class Board implements BoardOp {

    private int value;
    private final String[][] board;

    ArrayList<Board> childBoards = new ArrayList<>();

    public Board(int value, String[][] board) {
        this.value = value;
        this.board = board;
    }

    public int getValue() {
        return value;
    }
    public String[][] getBoard() {
        return board;
    }

    //-----------------------------------------------
    @Override
    public void printBoard() {
        String[][] currentBoard = getBoard();

        for (String[] row : currentBoard) {
            for (int col = 0; col < currentBoard.length; col++) {

                System.out.print(row[col] + "|");

            }
            System.out.println();
        }
        System.out.println("_______________");
    }

    @Override
    public Board copyBoard() {
        return null;
    }

    @Override
    public boolean hasEmptyCells() {

        String[][] currentBoard = board;

        for (String[] strings : currentBoard) {
            for (int j = 0; j < currentBoard.length; j++) {
                if (Objects.equals(strings[j], " ")) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void setNextBoard() {

    }

    @Override
    public void createChildBoards(Board board, String marker) {
        String[][] currentBoard = board.getBoard();

        for (int row = 0; row < currentBoard.length; row++) {
            for (int col = 0; col < currentBoard[row].length; col++) {

                if (Objects.equals(currentBoard[row][col], " ")) {

                    String[][] newBoard = new String[currentBoard.length][currentBoard[row].length];
                    for (int i = 0; i < currentBoard.length; i++) {
                        newBoard[i] = currentBoard[i].clone();
                    }
                    newBoard[row][col] = marker;

                    Board newChildBoard = new Board(0, newBoard);
                    childBoards.add(newChildBoard);
                }
            }
        }
    }


    @Override
    public ArrayList<Board> getChildBoards() {
        return childBoards;
    }

    @Override
    public boolean isValidMove(int row, int col) {
        return false;
    }

    @Override
    public void placeMarker(String mark, int row, int col) {

    }

    @Override
    public void checkWinner() {
        // Check rows
        for (String[] row : board) {
            if (row[0].equals(row[1]) &&
                    row[1].equals(row[2]) &&
                    !row[0].equals(" ")) {

                value = 1;
                break;
            }
        }
        // Check columns
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i].equals(board[1][i]) &&
                    board[1][i].equals(board[2][i]) &&
                    !board[0][i].equals(" ")) {

                value = 1;
                break;
            }
        }

        // Check diagonals
        if (board[0][0].equals(board[1][1]) &&
                board[1][1].equals(board[2][2]) &&
                !board[0][0].equals(" ")) {

            value = 1;
            return;
        }

        if (board[0][2].equals(board[1][1]) &&
                board[1][1].equals(board[2][0]) &&
                !board[0][2].equals(" ")) {

            value = 1;
        }
    }
}
