package view;

import controller.GameHandler;
import model.Board;

import java.util.Objects;
import java.util.Scanner;

public class GameView {

    GameHandler gameHandler;
    Scanner scanner;
    Board rootBoard;

    public GameView(Board rootBoard, GameHandler gameHandler, Scanner scanner) {
        this.gameHandler = gameHandler;
        this.scanner = scanner;
        this.rootBoard = rootBoard;
    }

    public void mainGameView() {


        while (true) {

            Board bestPlay = gameHandler.playerMakeMove();
            System.out.println("------------------");
            System.out.println("Your best play:");
            bestPlay.printBoard();

            System.out.println("Current board:");
            rootBoard.printBoard();

            System.out.println("Your move: ");

            System.out.println("Row:");
            int row = scanner.nextInt();

            System.out.println("Col:");
            int col = scanner.nextInt();

            gameHandler.playerMakeMove(row, col);

            if (hasResult("You win")) {
                rootBoard.printBoard();
                return;
            }

            gameHandler.computerMakeMove();
            System.out.println("------------------");

            if (hasResult("Computer wins")) {
                rootBoard.printBoard();
                return;
            }
        }
    }

    private boolean hasResult(String winner) {
        if (Objects.equals(rootBoard.checkBoard(rootBoard), "win")) {
            System.out.println(winner);
            return true;
        } else if (Objects.equals(rootBoard.checkBoard(rootBoard), "draw")) {
            System.out.println("It's a draw");
            return true;
        }
        return false;
    }
}