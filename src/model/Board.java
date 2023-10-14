package model;

import java.util.ArrayList;
import java.util.Objects;

public class Board implements BoardOp {

    private int value;
    private String[][] board;

    ArrayList<Board> childBoards = new ArrayList<>();

    public Board(int value, String[][] board) {
        this.value = value;
        this.board = board;
    }

    public int getValue() {
        return value;
    }

    public String[][] getBoard() {
        return  this.board;
    }


    public void setBoard(String [][] newBoard) {
        board = newBoard;
    }

    public void emptyChildBoards() {
        this.childBoards.clear();
    }

    //-----------------------------------------------
    @Override
    public void printBoard() {
        String[][] currentBoard = this.board;

        for (String[] row : currentBoard) {
            for (int col = 0; col < currentBoard.length; col++) {

                System.out.print(row[col] + "|");

            }
            System.out.println();
        }
        System.out.println("_______________");
    }

    @Override
    public Board copyBoard(Board board) {
        return board;
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
    public void createChildBoards(Board board,String marker) {

        if (value != 0) {
            return;
        }

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
        return Objects.equals(this.board[row][col], " ");
    }

    @Override
    public void placeMarker(String mark, int row, int col) {
        this.board[row][col] = mark;
    }

    @Override
    public void checkValue() {
        // Check rows
        for (String[] row : board) {
            if (row[0].equals(row[1]) && row[1].equals(row[2]) && !row[0].equals(" ")) {
                if (row[0].equals("X")) {
                    value += 1; // Player wins
                } else if (row[0].equals("O")) {
                    value -= 1; // Computer wins
                }
                return;
            }
        }

        // Check columns
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals(" ")) {
                if (board[0][i].equals("X")) {
                    value += 1; // Player wins
                } else if (board[0][i].equals("O")) {
                    value -= 1; // Computer wins
                }
                return;
            }
        }

        // Check diagonals
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals(" ")) {
            if (board[0][0].equals("X")) {
                value += 1; // Player wins
            } else if (board[0][0].equals("O")) {
                value -= 1; // Computer wins
            }
            return;
        }

        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals(" ")) {
            if (board[0][2].equals("X")) {
                value += 1; // Player wins
            } else if (board[0][2].equals("O")) {
                value -= 1; // Computer wins
            }
        }
    }

    public boolean isWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (checkLine(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (checkLine(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }

        // Check diagonals
        if (checkLine(board[0][0], board[1][1], board[2][2]) || checkLine(board[0][2], board[1][1], board[2][0])) {
            return true;
        }

        return false;
    }
    private boolean checkLine(String a, String b, String c) {
        return (!a.equals(" ")) && (a.equals(b)) && (b.equals(c));
    }
}
