package main;

import controller.GameHandler;
import model.TwoPlayerBoard;
import model.TicTacToeBoard;
import view.GameView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String[][] initBoard = {
                {" "," "," "},
                {" "," "," "},
                {" "," "," "}
        };

        Scanner playerInput = new Scanner(System.in);
        TwoPlayerBoard rootBoard = new TicTacToeBoard(initBoard,"X","O");
        GameHandler gameHandler = new GameHandler(rootBoard);
        GameView gameView = new GameView(rootBoard,gameHandler,playerInput);

        gameView.mainGameView();
    }
}