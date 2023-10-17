package model;

import java.util.ArrayList;

public interface BoardOp {

    void printBoard();
    boolean hasEmptyCells();
    void createChildBoards(Board board,String marker);
    ArrayList<Board> getChildBoards();
    boolean isValidMove(int row, int col);
    void placeMarker(String mark, int row, int col);
    int evalMove(int depth);
}
