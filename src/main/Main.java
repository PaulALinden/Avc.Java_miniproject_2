package main;

import controller.GameHandler;
import model.TwoPlayerBoard;
import model.TicTacToe;
import view.GameView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String[][] initBoard = {
                {" "," "," "},
                {" "," "," "},
                {" "," "," "}
        };

        String[][] testBoard = {
                {"X","O","X"},
                {"O","X"," "},
                {"O"," "," "}
        };

        Scanner playerInput = new Scanner(System.in);
        TwoPlayerBoard rootBoard = new TicTacToe(testBoard,"X","O");
        GameHandler gameHandler = new GameHandler(rootBoard);
        GameView gameView = new GameView(rootBoard,gameHandler,playerInput);

        gameView.mainGameView();
    }
}