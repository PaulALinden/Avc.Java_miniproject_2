package model;

import java.util.ArrayList;
import java.util.Objects;

public class Board implements BoardOp {
    private String[][] board;
    private final ArrayList<Board> POSSIBLE_BOARDS;
    final String PLAYER = "X";
    final String OPPONENT = "O";
    final String EMPTY = " ";
    public Board(String[][] board) {
        this.board = board;
        this.POSSIBLE_BOARDS = new ArrayList<>();
    }

    public String[][] getBoard() {
        return this.board;
    }
    public void setBoard(String[][] newBoard) {
        this.board = newBoard;
    }
    @Override
    public void printBoard() {

        for (String[] row : this.board) {
            for (int col = 0; col < board.length; col++) {
                System.out.print(row[col] + "|");
            }
            System.out.println();
        }
    }
    @Override
    public boolean hasNoEmptyCells() {

        for (String[] row : this.board) {
            for (int col = 0; col < board.length; col++) {
                if (Objects.equals(row[col], EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public void addMarkerToBoard(String mark, int row, int col) {
        this.board[row][col] = mark;
    }
    @Override
    public void createPossibleBoards(String marker) {
        for (int row = 0; row < this.board.length; row++) {
            for (int col = 0; col < this.board[row].length; col++) {

                if (Objects.equals(this.board[row][col], EMPTY)) {

                    String[][] newBoard = new String[this.board.length][this.board[row].length];
                    for (int i = 0; i < newBoard.length; i++) {
                        System.arraycopy(this.board[i], 0, newBoard[i], 0, this.board[i].length);
                    }
                    newBoard[row][col] = marker;
                    Board newChildBoard = new Board(newBoard);
                    this.POSSIBLE_BOARDS.add(newChildBoard);
                }
            }
        }
    }
    @Override
    public ArrayList<Board> getPossibleBoards() {
        return this.POSSIBLE_BOARDS;
    }
    @Override
    public int evaluateBoard() {
        final int WIN_SCORE = 10;
        final int LOSE_SCORE = -10;
        final int DRAW_SCORE = 0;

        // Check rows
        for (int i = 0; i <3; i++) {
            if (this.board[i][0].equals(this.board[i][1]) && this.board[i][1].equals(this.board[i][2]) && !this.board[i][0].equals(EMPTY)) {
                if (this.board[i][0].equals(PLAYER)) {
                    return WIN_SCORE; // Player wins
                } else if (this.board[i][0].equals(OPPONENT)) {
                    return LOSE_SCORE; // Computer wins
                }
            }
        }

        // Check columns
        for (int i = 0; i <3; i++) {
            if (this.board[0][i].equals(this.board[1][i]) && this.board[1][i].equals(this.board[2][i]) && !this.board[0][i].equals(EMPTY)) {
                if (this.board[0][i].equals(PLAYER)) {
                    return WIN_SCORE; // Player wins
                } else if (this.board[0][i].equals(OPPONENT)) {
                    return LOSE_SCORE; // Computer wins
                }
            }
        }
        // Check diagonals
        if (this.board[0][0].equals(this.board[1][1]) && this.board[1][1].equals(this.board[2][2]) && !this.board[0][0].equals(EMPTY)) {
            if (this.board[0][0].equals(PLAYER)) {
                return WIN_SCORE; // Player wins
            } else if (this.board[0][0].equals(OPPONENT)) {
                return LOSE_SCORE; // Computer wins
            }
        }

        if (this.board[0][2].equals(this.board[1][1]) && this.board[1][1].equals(this.board[2][0]) && !this.board[0][2].equals(EMPTY)) {
            if (this.board[0][2].equals(PLAYER)) {
                return WIN_SCORE; // Player wins
            } else if (this.board[0][2].equals(OPPONENT)) {
                return LOSE_SCORE; // Computer wins
            }
        }

        return DRAW_SCORE;
    }
    public String checkBoardState() {
        final String WIN = "win";
        final String LOSE = "draw";
        final String CONTINUE = "continue";

        if (isWinner()) {
            return WIN;
        } else if (!isWinner() && hasNoEmptyCells()) {
            return LOSE;
        }
        return CONTINUE;
    }
    public boolean isWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (checkLine(this.board[i][0], this.board[i][1], this.board[i][2])) {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (checkLine(this.board[0][i], this.board[1][i], this.board[2][i])) {
                return true;
            }
        }
        // Check diagonals
        return checkLine(this.board[0][0], this.board[1][1], this.board[2][2])
                || checkLine(this.board[0][2], this.board[1][1], this.board[2][0]);
    }
    private boolean checkLine(String a, String b, String c) {
        return (!a.equals(EMPTY)) && (a.equals(b)) && (b.equals(c));
    }
}