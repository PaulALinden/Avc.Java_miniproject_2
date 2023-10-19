package model;

import java.util.ArrayList;
import java.util.Objects;

public class Board implements BoardOp {
    private String[][] board;
    private final ArrayList<Board> childBoards;
    final String EMPTY = " ";

    public Board(String[][] board) {
        this.board = board;
        this.childBoards = new ArrayList<>();
    }
    public String[][] getBoard() {
        return this.board;
    }
    public void setBoard(String[][] newBoard) {
        board = newBoard;
    }
    @Override
    public void printBoard() {
        String[][] currentBoard = this.board.clone();

        for (String[] row : currentBoard) {
            for (int col = 0; col < currentBoard.length; col++) {

                System.out.print(row[col] + "|");

            }
            System.out.println();
        }
    }
    @Override
    public boolean hasEmptyCells(Board board) {

        String[][] currentBoard = board.getBoard();

        for (String[] row : currentBoard) {
            for (int col = 0; col < currentBoard.length; col++) {
                if (Objects.equals(row[col], EMPTY)) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public void createChildBoards(String [][] board, String marker) {

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {

                if (Objects.equals(board[row][col], EMPTY)) {

                    String[][] newBoard = new String[board.length][board[row].length];
                    for (int i = 0; i < newBoard.length; i++) {
                        System.arraycopy(board[i], 0, newBoard[i], 0, board[i].length);
                    }
                    newBoard[row][col] = marker;
                    Board newChildBoard = new Board(newBoard);
                    childBoards.add(newChildBoard);
                }
            }
        }
    }
    @Override
    public ArrayList<Board> getChildBoards() {
        return this.childBoards;
    }
    @Override
    public void placeMarker(String mark, int row, int col) {
        this.board[row][col] = mark;
    }
    @Override
    public int evalMove(int depth) {
        final int WINSCORE = 10 - depth;
        final int LOSESCORE = -10 + depth;
        final int DRAWSCORE = 0;

        final String PLAYER = "X";
        final String OPPONENT = "O";

        // Check rows
        for (int i = 0; i <3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals(" ")) {
                if (board[i][0].equals(PLAYER)) {
                    return WINSCORE; // Player wins
                } else if (board[i][0].equals(OPPONENT)) {
                    return LOSESCORE; // Computer wins
                }
            }
        }
        // Check columns
        for (int i = 0; i <3; i++) {
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals(" ")) {
                if (board[0][i].equals(PLAYER)) {
                    return WINSCORE; // Player wins
                } else if (board[0][i].equals(OPPONENT)) {
                    return LOSESCORE; // Computer wins
                }
            }
        }
        // Check diagonals
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals(" ")) {
            if (board[0][0].equals(PLAYER)) {
                return WINSCORE; // Player wins
            } else if (board[0][0].equals(OPPONENT)) {
                return LOSESCORE; // Computer wins
            }
        }

        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals(" ")) {
            if (board[0][2].equals(PLAYER)) {
                return WINSCORE; // Player wins
            } else if (board[0][2].equals(OPPONENT)) {
                return LOSESCORE; // Computer wins
            }
        }

        return DRAWSCORE;
    }
    public String checkBoard(Board board) {
        if (board.isWinner()) {
            return "win";
        } else if (!board.isWinner() && !board.hasEmptyCells(board)) {
            return "draw";
        }
        return "continue";
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
        return checkLine(board[0][0], board[1][1], board[2][2]) || checkLine(board[0][2], board[1][1], board[2][0]);
    }
    private boolean checkLine(String a, String b, String c) {
        return (!a.equals(EMPTY)) && (a.equals(b)) && (b.equals(c));
    }
}