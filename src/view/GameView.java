package view;

import controller.GameHandler;
import model.GameStates;
import model.TwoPlayerBoard;

import java.util.Scanner;

public class GameView {
    private final GameHandler gameHandler;
    private final Scanner playerInput;
    private final TwoPlayerBoard rootBoard;
    public GameView(TwoPlayerBoard rootBoard, GameHandler gameHandler, Scanner playerInput) {
        this.gameHandler = gameHandler;
        this.playerInput = playerInput;
        this.rootBoard = rootBoard;
    }
    public void mainGameView() {
        rootBoard.setGameStates(GameStates.PLAYING);

        while (rootBoard.getGameStates() == GameStates.PLAYING) {
            playersTurn();

            if (checkResult(rootBoard.getPlayer())) {
                return;
            }

            computersTurn();

            if (checkResult(rootBoard.getOpponent())) {
                return;
            }
        }
    }
    private void computersTurn() {
        this.gameHandler.optimalMoveComputer();
        System.out.println("------------------");
    }
    private void playersTurn() {
        TwoPlayerBoard bestPlay = this.gameHandler.optimalMovePlayer();
        boolean isValidMove = false;
        System.out.println("------------------");
        System.out.println("Your best play:");
        bestPlay.displayBoard();

        System.out.println("Current board:");
        rootBoard.displayBoard();

        System.out.println("Your move: ");

        while (!isValidMove) {

            System.out.println("Row:");
            int row = playerInput.nextInt();

            System.out.println("Col:");
            int col = playerInput.nextInt();

            isValidMove = this.gameHandler.setMarker(row, col);

            if (!isValidMove) {
                System.out.println("Not valid move...");
            }
        }
    }
    private boolean checkResult(String player) {
        if (gameHandler.hasResult() == GameStates.WIN) {
            System.out.println(player + " wins!");
            this.rootBoard.displayBoard();
            return true;
        } else if (gameHandler.hasResult() == GameStates.DRAW) {
            System.out.println("It's a draw!");
            return true;
        }
        return false;
    }
}