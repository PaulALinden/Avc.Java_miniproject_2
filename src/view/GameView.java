package view;

import controller.GameHandler;
import model.Board;

import java.util.Objects;
import java.util.Scanner;

public class GameView {

    GameHandler gameHandler;
    Scanner playerInput;
    Board rootBoard;

    public GameView(Board rootBoard, GameHandler gameHandler, Scanner playerInput) {
        this.gameHandler = gameHandler;
        this.playerInput = playerInput;
        this.rootBoard = rootBoard;
    }

    public void mainGameView() {

        while (true) {

            playersTurn();

            if (hasResult("You win")) {
                this.rootBoard.printBoard();
                return;
            }

            computersTurn();

            if (hasResult("Computer wins")) {
                this.rootBoard.printBoard();
                return;
            }
        }
    }

    private void computersTurn() {
        this.gameHandler.optimalMoveComputer();
        System.out.println("------------------");
    }

    private void playersTurn() {
        Board bestPlay = this.gameHandler.optimalMovePlayer();
        System.out.println("------------------");
        System.out.println("Your best play:");
        bestPlay.printBoard();

        System.out.println("Current board:");
        rootBoard.printBoard();

        System.out.println("Your move: ");

        System.out.println("Row:");
        int row = playerInput.nextInt();

        System.out.println("Col:");
        int col = playerInput.nextInt();

        this.gameHandler.setMarker(row, col);
    }
    private boolean hasResult(String winner) {
        if (Objects.equals(this.rootBoard.checkBoardState(), "win")) {
            System.out.println(winner);
            return true;
        } else if (Objects.equals(this.rootBoard.checkBoardState(), "draw")) {
            System.out.println("It's a draw");
            return true;
        }
        return false;
    }
}