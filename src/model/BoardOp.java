package model;

import java.util.ArrayList;

public interface BoardOp {

    void printBoard();
    boolean hasNoEmptyCells();
    void createPossibleBoards(String marker);
    ArrayList<Board> getPossibleBoards();
    void addMarkerToBoard(String mark, int row, int col);
    int evaluateBoard();
}
