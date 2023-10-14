import model.Board;

import java.util.Scanner;

import static controller.MinMax.minMax;

public class Main {

    public static void main(String[] args) {

        String[][] initBoard2 = {
                {"X", "O", "X"},
                {" ", "O", " "},
                {" ", " ", " "}
        };

        Board root = new Board(0, initBoard2);
        Scanner scanner = new Scanner(System.in);
        root.printBoard();

        //Init game
        System.out.println("Row:");
        int row = scanner.nextInt();
        System.out.println("Col:");
        int col = scanner.nextInt();

        root.placeMarker("X", row, col);

        while (true) {
            if (root.isWinner()) {
                System.out.println("<<<<<<<>>>>>>>>>>");
                root.printBoard();
                System.out.println("You won");
                return;
            } else if (!root.isWinner() && !root.hasEmptyCells()) {
                System.out.println("<<<<<<<>>>>>>>>>>");
                root.printBoard();
                System.out.println("Its a draw");
                return;
            }

            //Computers turn
            if (root.hasEmptyCells()) {

                root.emptyChildBoards();
                root.createChildBoards(root, "O");

                Board bestChildComp = null;
                int bestResult = Integer.MAX_VALUE;

                for (Board child : root.getChildBoards()) {
                    int result = minMax(child, 10, false);
                    System.out.println("Computer results" +result);
                    if (result < bestResult) {
                        bestResult = result;
                        bestChildComp = child;
                    }
                }

                // Apply the computer's move to the current board
                assert bestChildComp != null;
                root.setBoard(bestChildComp.getBoard());

                // Check if computer is the winner
                if (root.isWinner()) {
                    System.out.println("<<<<<<<>>>>>>>>>>");
                    root.printBoard();
                    System.out.println("Computer won");
                    return;
                }else {
                    System.out.println("Comp move");
                    root.printBoard();
                }
            }

            //Players turn
            if (root.hasEmptyCells()) {

                root.emptyChildBoards();
                root.createChildBoards(root, "X");

                Board bestChild = null;
                int bestResult = Integer.MIN_VALUE;
                // Checks best outcome
                for (Board child : root.getChildBoards()) {
                    int result = minMax(child, 10, true);

                    System.out.println("Player results" +result);

                    if (result > bestResult) {
                        bestResult = result;
                        bestChild = child;
                    }
                }

                if (bestChild != null) {
                    System.out.println("Best option for Player:");
                    bestChild.printBoard();
                }

                System.out.println("Row:");
                row = scanner.nextInt();
                System.out.println("Col:");
                col = scanner.nextInt();
                root.placeMarker("X", row, col);

                System.out.println("Your move");
                root.printBoard();
            }
        }
    }
}