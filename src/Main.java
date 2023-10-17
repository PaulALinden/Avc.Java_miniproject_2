import controller.GameHandler;
import model.Board;
import view.GameView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String[][] initBoard1 = {
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}

        };
        String[][] initBoard2 = {
                {"X", "O", "X"},
                {"O", "X", " "},
                {"X", "O", " "}

        };

        Scanner scanner = new Scanner(System.in);
        Board rootBoard = new Board(0, initBoard1);
        GameHandler gameHandler = new GameHandler(rootBoard);
        GameView gameView = new GameView(rootBoard,gameHandler,scanner);

        gameView.mainGameView();
    }
}