import controller.GameHandler;
import model.Board;
import view.GameView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final String EMPTY = " ";

        String[][] initBoard = {
                {EMPTY,EMPTY,EMPTY},
                {EMPTY,EMPTY,EMPTY},
                {EMPTY,EMPTY,EMPTY}
        };

        Scanner scanner = new Scanner(System.in);
        Board rootBoard = new Board(initBoard);
        GameHandler gameHandler = new GameHandler(rootBoard);
        GameView gameView = new GameView(rootBoard,gameHandler,scanner);

        gameView.mainGameView();
    }
}