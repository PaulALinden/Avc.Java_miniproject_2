import model.Board;

import java.util.ArrayList;

import static controller.MinMax.minMax;

public class Main {

    public static void main(String[] args) {

        String [][] initBoard = { {"X", "O", "X"},
                                  {"O", "O", "X"},
                                  {" ", "X", " "} };;
        Board root = new Board(0, initBoard);

        if (root.hasEmptyCells()) {
            root.createChildBoards(root, "O");
        }

        for (Board child: root.getChildBoards()) {
            int result = minMax(child, 10, true);
            child.printBoard();
            System.out.println("Resultat drag: ");
            System.out.println(result);
            System.out.println("|||||||||||||||||");
        }
    }
}