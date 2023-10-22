package model;

import java.util.ArrayList;
import java.util.Objects;

public class TicTacToe extends TwoPlayerBoard {
    public TicTacToe(String[][] board, String player, String opponent) {
        super(board, player, opponent);
    }
    @Override
    public void displayBoard() {

        for (String[] row : this.board) {
            for (int col = 0; col < board.length; col++) {
                System.out.print(row[col] + "|");
            }
            System.out.println();
        }
    }
    @Override
    public String[][] getBoard() {
        return this.board;
    }
    @Override
    public void setBoard(String[][] newBoard) {
        this.board = newBoard;
    }
    @Override
    public ArrayList<TwoPlayerBoard> getPossibleBoards() {
        return this.possibleBoards;
    }
    @Override
    public void createPossibleBoards(String marker) {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (Objects.equals(board[row][col], super.empty)) {

                    String[][] newBoard = new String[3][3];
                    for (int i = 0; i < 3; i++) {
                        System.arraycopy(board[i], 0, newBoard[i], 0, board[i].length);
                    }

                    newBoard[row][col] = marker;
                    this.possibleBoards.add(new TicTacToe(newBoard, "X", "O"));
                }
            }
        }
    }
    @Override
    public void addMarkerToBoard(String mark, int row, int col) {
        this.board[row][col] = mark;
    }
    @Override
    public boolean hasNoEmptyCells() {

        for (String[] row : this.board) {
            for (int col = 0; col < board.length; col++) {
                if (Objects.equals(row[col], super.empty)) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public int evaluateBoard() {
        final int WIN_SCORE = 10;
        final int LOSE_SCORE = -10;
        final int DRAW_SCORE = 0;

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (this.board[i][0].equals(this.board[i][1]) && this.board[i][1].equals(this.board[i][2]) && !this.board[i][0].equals(super.empty)) {
                if (this.board[i][0].equals(player)) {
                    return WIN_SCORE; // Player wins
                } else if (this.board[i][0].equals(opponent)) {
                    return LOSE_SCORE; // Computer wins
                }
            }

        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (this.board[0][i].equals(this.board[1][i]) && this.board[1][i].equals(this.board[2][i]) && !this.board[0][i].equals(super.empty)) {
                if (this.board[0][i].equals(player)) {
                    return WIN_SCORE; // Player wins
                } else if (this.board[0][i].equals(opponent)) {
                    return LOSE_SCORE; // Computer wins
                }
            }
        }
        // Check diagonals
        if (this.board[0][0].equals(this.board[1][1]) && this.board[1][1].equals(this.board[2][2]) && !this.board[0][0].equals(super.empty)) {
            if (this.board[0][0].equals(player)) {
                return WIN_SCORE; // Player wins
            } else if (this.board[0][0].equals(opponent)) {
                return LOSE_SCORE; // Computer wins
            }
        }

        if (this.board[0][2].equals(this.board[1][1]) && this.board[1][1].equals(this.board[2][0]) && !this.board[0][2].equals(super.empty)) {
            if (this.board[0][2].equals(player)) {
                return WIN_SCORE; // Player wins
            } else if (this.board[0][2].equals(opponent)) {
                return LOSE_SCORE; // Computer wins
            }
        }

        return DRAW_SCORE;
    }
    @Override
    public void checkBoardState() {
        if (isWinner()) {
             setGameStates(GameStates.WIN);
        } else if (!isWinner() && hasNoEmptyCells()) {
            setGameStates(GameStates.DRAW);
        }
    }
    @Override
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
        return (!a.equals(super.empty)) && (a.equals(b)) && (b.equals(c));
    }
}
