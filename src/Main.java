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

        Scanner scanner = new Scanner(System.in);
        Board rootBoard = new Board(initBoard1,0,0);
        GameHandler gameHandler = new GameHandler(rootBoard);
        GameView gameView = new GameView(rootBoard,gameHandler,scanner);

        gameView.mainGameView();
    }
}